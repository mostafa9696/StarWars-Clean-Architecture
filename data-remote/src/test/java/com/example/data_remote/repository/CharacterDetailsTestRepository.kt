package com.example.data_remote.repository

import com.example.data_remote.BaseTest
import com.example.data_remote.utils.Constants.EXISTING_CHARACTER_URL
import com.example.data_remote.utils.Constants.FILM_URL
import com.example.data_remote.utils.Constants.PLANET_URL
import com.example.data_remote.utils.Constants.SPECIES_URL
import com.example.domain.repository.ICharacterDetailsRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CharacterDetailsTestRepository : BaseTest() {

    private lateinit var characterDetailsRepository: ICharacterDetailsRepository

    @Before
    override fun setup() {
        super.setup()
        characterDetailsRepository = CharacterDetailsRepository(apiService)
    }

    @Test
    fun `given existing character url then return details URLs`() = runBlocking {
        val detailsURLs = characterDetailsRepository.getCharacterURLsDetails(EXISTING_CHARACTER_URL)
        detailsURLs.collect {
            Truth.assertThat(it.homeworldUrl).isNotEmpty()
            Truth.assertThat(it.specieUrls).isNotEmpty()
            Truth.assertThat(it.filmUrls).isNotEmpty()
        }
    }

    @Test
    fun `given character planet url then return planet details`() = runBlocking {
        val planet = characterDetailsRepository.getCharacterPlanet(PLANET_URL)
        Truth.assertThat(planet.name).isEqualTo("Tatooine")
        Truth.assertThat(planet.population).isEqualTo("120000")
    }

    @Test
    fun `given character species url then return species detail`() = runBlocking {
        val species = characterDetailsRepository.getCharacterSpecies(listOf(SPECIES_URL))
        Truth.assertThat(species).isNotEmpty()
        Truth.assertThat(species[0].name).isEqualTo("Wookie")
    }

    @Test
    fun `given character films url then return films detail`() = runBlocking {
        val films = characterDetailsRepository.getCharacterFilms(listOf(FILM_URL))
        Truth.assertThat(films).isNotEmpty()
        Truth.assertThat(films[0].title).isEqualTo("A New Hope")
    }
}