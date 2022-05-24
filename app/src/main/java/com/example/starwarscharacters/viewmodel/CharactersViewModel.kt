package com.example.starwarscharacters.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.usecases.SearchUseCase
import com.example.starwarscharacters.mappers.toPresentation
import com.example.starwarscharacters.models.CharacterPresentation
import com.example.starwarscharacters.models.DataResource
import com.example.starwarscharacters.models.ErrorResponse
import com.example.starwarscharacters.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

class CharactersViewModel(
    private val searchUseCase: SearchUseCase
): BaseViewModel() {

    private val _searchLiveData = MutableLiveData<DataResource<List<CharacterPresentation>>>()

    val searchLiveData: LiveData<DataResource<List<CharacterPresentation>>>
        get() = _searchLiveData

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val messageID = ExceptionHandler.parse(exception)
        onSearchError(messageID)
    }

    private fun onSearchError(messageID: Int) {
        _searchLiveData.value = DataResource.Error(errorResponse = ErrorResponse(messageID))
    }

    fun searchCharacters(characterName: String) {
        job?.cancel()
        job = launchCoroutine {
            delay(500)
            _searchLiveData.value = DataResource.Loading()
            searchUseCase.invoke(characterName).collect { characters ->
                val charactersPresentation = characters.map { character -> character.toPresentation() }
                _searchLiveData.value = DataResource.Success(charactersPresentation)

            }
        }
    }
}