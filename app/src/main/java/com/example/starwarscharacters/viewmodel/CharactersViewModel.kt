package com.example.starwarscharacters.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.usecases.GetAllFavoritesUseCase
import com.example.domain.usecases.SearchUseCase
import com.example.starwarscharacters.mappers.toPresentation
import com.example.starwarscharacters.models.CharacterPresentation
import com.example.starwarscharacters.models.DataResource
import com.example.starwarscharacters.models.ErrorResponse
import com.example.starwarscharacters.models.FavoritePresentation
import com.example.starwarscharacters.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect

class CharactersViewModel(
    private val searchUseCase: SearchUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase
) : BaseViewModel() {

    private val _searchLiveData = MutableLiveData<DataResource<List<CharacterPresentation>>>()

    val searchLiveData: LiveData<DataResource<List<CharacterPresentation>>>
        get() = _searchLiveData

    private val _favoritesLiveData = MutableLiveData<List<FavoritePresentation>>()

    val favoritesLiveData: LiveData<List<FavoritePresentation>>
        get() = _favoritesLiveData

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
                val charactersPresentation =
                    characters.map { character -> character.toPresentation() }
                _searchLiveData.value = DataResource.Success(charactersPresentation)
            }
        }
    }

    fun getFavorites() {
        launchCoroutine {
            getAllFavoritesUseCase(Unit).collect { favorites ->
                _favoritesLiveData.value = favorites.map { it.toPresentation() }
            }
        }
    }
}