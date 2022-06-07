package com.example.starwarscharacters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.starwarscharacters.utils.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.koin.test.AutoCloseKoinTest
import org.mockito.MockitoAnnotations


abstract class BaseViewModelTest : AutoCloseKoinTest(){
    /**
     * Swaps the background executor used by the Architecture Components with a different one which
     * executes each task synchronously to could use LiveData.
     **/
    @get:Rule
    open val instantExecutorRule = InstantTaskExecutorRule()

    /**
     * A test rule to allow testing coroutines that use the main dispatcher
     */
    @ExperimentalCoroutinesApi
    @get:Rule
    open val coroutineTestRule = CoroutineTestRule()

    open fun setup() {
        MockitoAnnotations.initMocks(this)
    }
}