package com.example.starwarscharacters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel: ViewModel(), CoroutineScope {

    protected var job: Job? = null
    abstract val coroutineExceptionHandler: CoroutineExceptionHandler
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + coroutineExceptionHandler

    protected fun launchCoroutine(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch {
            block()
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}