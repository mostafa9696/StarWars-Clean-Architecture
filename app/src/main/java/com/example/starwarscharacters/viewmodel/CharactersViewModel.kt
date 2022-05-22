package com.example.starwarscharacters.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.usecases.SearchUseCase
import com.example.starwarscharacters.models.DataResource
import com.example.starwarscharacters.models.ErrorResponse
import com.example.starwarscharacters.utils.ExceptionHandler
import kotlinx.coroutines.CoroutineExceptionHandler

class CharactersViewModel(
    searchUseCase: SearchUseCase
): BaseViewModel() {

    private val _searchLiveData = MutableLiveData<DataResource<List<CharProgression>>>()

    val searchLiveData: LiveData<DataResource<List<CharProgression>>>
        get() = _searchLiveData

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val messageID = ExceptionHandler.parse(exception)
        onSearchError(messageID)
    }

    private fun onSearchError(messageID: Int) {
        _searchLiveData.value = DataResource.Error(errorResponse = ErrorResponse(messageID))
    }

    fun logg() {
        Log.d("ww1", "logg: ")
    }

}