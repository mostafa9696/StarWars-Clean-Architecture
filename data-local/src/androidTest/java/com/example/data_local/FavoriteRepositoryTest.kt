package com.example.data_local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data_local.repository.FavoriteRepository
import com.example.domain.repository.IFavoritesRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoriteRepositoryTest : BaseTest() {
    private lateinit var favoriteRepository: IFavoritesRepository

    @Before
    override fun setup() {
        super.setup()
        favoriteRepository = FavoriteRepository(dao)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun testGetAllFavorites() = runBlocking {

        favoriteRepository.insertFavorite(favorite)

        val favorites = favoriteRepository.getAllFavorites().first()
        Truth.assertThat(favorites[0]).isEqualTo(favorite)
/*
        favorites.collect {
            // Truth.assertThat(it).isNotEmpty()
            Truth.assertThat(it[0]).isEqualTo(favorite)
        }*/

        // favoriteRepository.insertFavorite(favorite).collect()

    }
}