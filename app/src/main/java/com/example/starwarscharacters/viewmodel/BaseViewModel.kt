package com.example.starwarscharacters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel()/*, CoroutineScope*/ {

    protected var job: Job? = null

    abstract val coroutineExceptionHandler: CoroutineExceptionHandler
    /*override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + coroutineExceptionHandler*/


    protected fun launchCoroutine(block: suspend CoroutineScope.() -> Unit): Job {
        /*
         Implement CoroutineScope interface and override coroutineContext and define (dispatcher, CoroutineExceptionHandler, job)
         when we use launch { } block

        return launch {
            block()
        }
         */

        /*
        in uiScope we create an object and define its scope and job inside its constructor
        we don't need to switch to background thread or Dispatchers.IO when we call retrofit or room as it handle switching to background thread by itself
        and it’s safe for us to call their suspend functions on the main
        use SupervisorJob which is used as a parent for all launched coroutines within the viewmodel and
        they will be cancelled in [onCleared]

        val viewModelJob = SupervisorJob()
        val uiScope =
            CoroutineScope(Dispatchers.Main + viewModelJob)
        return uiScope.launch(coroutineExceptionHandler) {
            block()
        }
         */

        /*
        viewModelScope means less boilerplate code
        All the CoroutineScope setup and cancellation is done for us
        cancel all blocks in this scope in onCleared() function by default
        Dispatchers.Main.immediate is set as the default CoroutineDispatcher for viewModelScope
         */
        return viewModelScope.launch(coroutineExceptionHandler) {
            block()
        }

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}