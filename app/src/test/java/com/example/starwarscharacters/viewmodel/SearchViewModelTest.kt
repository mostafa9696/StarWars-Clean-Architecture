package com.example.starwarscharacters.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.domain.usecases.GetAllFavoritesUseCase
import com.example.domain.usecases.SearchUseCase
import com.example.starwarscharacters.BaseViewModelTest
import com.example.starwarscharacters.utils.Data
import com.example.starwarscharacters.utils.observeOnce
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class SearchViewModelTest : BaseViewModelTest() {

    @Mock
    lateinit var searchUseCase: SearchUseCase
    @Mock
    lateinit var favoritesUseCase: GetAllFavoritesUseCase

    lateinit var searchViewModel: CharactersViewModel

    @Before
    override fun setup() {
        super.setup()
        searchViewModel = CharactersViewModel(searchUseCase, favoritesUseCase)
    }

    @Test
    fun `given a search parameter when search executed then return success state`() {
        coroutineTestRule.dispatcher.runBlockingTest {
            Mockito.`when`(searchUseCase("Darth")).thenReturn(
               flow { emit(Data.characters) }
            )

            searchViewModel.searchCharacters(Data.SEARCH_PARAM)
            advanceTimeBy(600)

            searchViewModel.searchLiveData.observeOnce {
                Truth.assertThat(it.data?.get(0)?.name).isEqualTo("Darth Vader")
                Truth.assertThat(it.data).isNotEmpty()
            }


        }
    }

}