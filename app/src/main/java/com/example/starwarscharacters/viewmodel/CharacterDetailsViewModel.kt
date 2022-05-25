package com.example.starwarscharacters.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.usecases.CharacterURLsDetailsUseCase
import com.example.domain.usecases.GetFilmsUseCase
import com.example.domain.usecases.GetPlanetUseCase
import com.example.domain.usecases.GetSpeciesUseCase
import com.example.starwarscharacters.mappers.toPresentation
import com.example.starwarscharacters.models.CharacterDetailsPresentation
import com.example.starwarscharacters.models.DataResource
import com.example.starwarscharacters.models.ErrorResponse
import com.example.starwarscharacters.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect

class CharacterDetailsViewModel(
    private val characterURLsDetailsUseCase: CharacterURLsDetailsUseCase,
    private val planetUseCase: GetPlanetUseCase,
    private val speciesUseCase: GetSpeciesUseCase,
    private val filmsUseCase: GetFilmsUseCase
) : BaseViewModel() {


    private val _characterDetailsLiveData =
        MutableLiveData<DataResource<CharacterDetailsPresentation>>()

    val characterDetailsLiveData: LiveData<DataResource<CharacterDetailsPresentation>>
        get() = _characterDetailsLiveData

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val messageID = ExceptionHandler.parse(exception)
        onSearchError(messageID)
    }

    fun getCharacterUrlsDetails(characterURL: String) {
        job = launchCoroutine {
            characterURLsDetailsUseCase.invoke(characterURL).collect {
                val planetResponse = async { planetUseCase.invoke(it.homeworldUrl) }
                val speciesResponse = async { speciesUseCase.invoke(it.specieUrls) }
                val filmsResponse = async { filmsUseCase.invoke(it.filmUrls) }

                _characterDetailsLiveData.value = DataResource.Success(CharacterDetailsPresentation(
                    planetResponse.await().toPresentation(),
                    speciesResponse.await().map { it.toPresentation() },
                    filmsResponse.await().map { it.toPresentation() }
                ))
            }
        }
    }

    private fun onSearchError(messageID: Int) {
        _characterDetailsLiveData.value =
            DataResource.Error(errorResponse = ErrorResponse(messageID))
    }

}