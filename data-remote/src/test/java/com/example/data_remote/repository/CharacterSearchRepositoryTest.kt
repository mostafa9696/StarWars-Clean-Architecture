package com.example.data_remote.repository

import com.example.data_remote.BaseTest
import com.example.data_remote.utils.Constants.EXISTING_SEARCH_PARAMS
import com.example.data_remote.utils.Constants.NON_EXISTENT_SEARCH_PARAMS
import com.example.domain.repository.ICharacterSearchRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CharacterSearchRepositoryTest : BaseTest() {

    private lateinit var characterSearchRepository: ICharacterSearchRepository

    @Before
    override fun setup() {
        super.setup()
        characterSearchRepository = CharacterSearchRepository(apiService)
    }

    @Test
    fun `search with existing search query characters then return list of search results`() =
        runBlocking {
            val results = characterSearchRepository.searchCharacters(EXISTING_SEARCH_PARAMS)
            results.collect {
                Truth.assertThat(it).isNotEmpty()
            }
        }

    @Test
    fun `search with non-existing search query characters then return empty list`() = runBlocking {
        val results = characterSearchRepository.searchCharacters(NON_EXISTENT_SEARCH_PARAMS)
        results.collect {
            Truth.assertThat(it).isEmpty()
        }
    }
}