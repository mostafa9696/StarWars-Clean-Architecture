package com.example.starwarscharacters.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.models.Result
import com.example.domain.usecases.*
import com.example.starwarscharacters.mappers.toDomain
import com.example.starwarscharacters.mappers.toPresentation
import com.example.starwarscharacters.models.CharacterDetailsPresentation
import com.example.starwarscharacters.models.DataResource
import com.example.starwarscharacters.models.ErrorResponse
import com.example.starwarscharacters.models.FavoritePresentation
import com.example.starwarscharacters.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect

class CharacterDetailsViewModel(
    private val characterURLsDetailsUseCase: CharacterURLsDetailsUseCase,
    private val planetUseCase: GetPlanetUseCase,
    private val speciesUseCase: GetSpeciesUseCase,
    private val filmsUseCase: GetFilmsUseCase,
    private val deleteFavoriteByNameUseCase: DeleteFavoriteByNameUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val getFavoriteByNameUseCase: GetFavoriteByNameUseCase
) : BaseViewModel() {


    private val _characterDetailsLiveData =
        MutableLiveData<DataResource<CharacterDetailsPresentation>>()

    val characterDetailsLiveData: LiveData<DataResource<CharacterDetailsPresentation>>
        get() = _characterDetailsLiveData

    val isFavoriteLiveData: LiveData<Boolean>
        get() = _isFavoriteLiveData

    private var _isFavoriteLiveData = MutableLiveData<Boolean>()

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val messageID = ExceptionHandler.parse(exception)
        onDetailsError(messageID)
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

    fun saveFavorite(favoritePresentation: FavoritePresentation) {
        launchCoroutine {
            insertFavoriteUseCase(favoritePresentation.toDomain()).collect { result ->
                if (result == Result.SUCCESS)
                    _isFavoriteLiveData.value = true
            }
        }
    }

    fun deleteFavorite(name: String) {
        launchCoroutine {
            deleteFavoriteByNameUseCase(name).collect { effectedRow ->
                if (effectedRow == 1)
                    _isFavoriteLiveData.value = false
            }
        }
    }

    fun isCharacterFavorite(name: String) {
        launchCoroutine {
            getFavoriteByNameUseCase(name).collect {
                _isFavoriteLiveData.value = it != null
            }
        }
    }

    private fun onDetailsError(messageID: Int) {
        _characterDetailsLiveData.value =
            DataResource.Error(errorResponse = ErrorResponse(messageID))
    }

}