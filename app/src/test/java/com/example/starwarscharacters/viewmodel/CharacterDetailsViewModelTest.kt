package com.example.starwarscharacters.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.domain.usecases.*
import com.example.starwarscharacters.BaseViewModelTest
import com.example.starwarscharacters.mappers.toPresentation
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
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class CharacterDetailsViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: CharacterDetailsViewModel

    @Mock
    private lateinit var characterURLsDetailsUseCase: CharacterURLsDetailsUseCase

    @Mock
    private lateinit var planetUseCase: GetPlanetUseCase

    @Mock
    private lateinit var speciesUseCase: GetSpeciesUseCase

    @Mock
    private lateinit var filmsUseCase: GetFilmsUseCase

    @Mock
    private lateinit var deleteFavoriteByNameUseCase: DeleteFavoriteByNameUseCase

    @Mock
    private lateinit var insertFavoriteUseCase: InsertFavoriteUseCase

    @Mock
    private lateinit var getFavoriteByNameUseCase: GetFavoriteByNameUseCase

    @Before
    override fun setup() {
        super.setup()
        viewModel = CharacterDetailsViewModel(
            characterURLsDetailsUseCase,
            planetUseCase,
            speciesUseCase,
            filmsUseCase,
            deleteFavoriteByNameUseCase,
            insertFavoriteUseCase,
            getFavoriteByNameUseCase
        )
    }

    @Test
    fun `given a character url when character details request sent then get character details`() {
        coroutineTestRule.dispatcher.runBlockingTest {

            Mockito.`when`(characterURLsDetailsUseCase(Data.CHARACTER_URL)).thenReturn(
                flow { emit(Data.characterDetailsURLs) }
            )
            Mockito.`when`(planetUseCase(Data.PLANET_URL)).thenReturn(
                Data.planet
            )
            Mockito.`when`(speciesUseCase(Data.SPECIES_URLS)).thenReturn(
                Data.species
            )
            Mockito.`when`(filmsUseCase(Data.FILMS_URLS)).thenReturn(
                Data.films
            )

            viewModel.getCharacterDetails(Data.CHARACTER_URL)

            verify(planetUseCase, times(1)).invoke(Data.PLANET_URL)
            verify(speciesUseCase, times(1)).invoke(Data.SPECIES_URLS)
            verify(filmsUseCase, times(1)).invoke(Data.FILMS_URLS)

            viewModel.characterDetailsLiveData.observeOnce {
                Truth.assertThat(it.data).isNotNull()
                Truth.assertThat(it.data?.planet).isEqualTo(Data.planet.toPresentation())
                Truth.assertThat(it.data?.species?.get(0)).isEqualTo(Data.species[0].toPresentation())
                Truth.assertThat(it.data?.films?.get(0)).isEqualTo(Data.films[0].toPresentation())
            }

        }
    }

}