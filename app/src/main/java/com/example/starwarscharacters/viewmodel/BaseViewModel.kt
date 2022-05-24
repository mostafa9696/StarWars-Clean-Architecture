package com.example.starwarscharacters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    protected var job: Job? = null
    abstract val coroutineExceptionHandler: CoroutineExceptionHandler

    protected fun launchCoroutine(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(coroutineExceptionHandler) {
            block()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}