package com.example.data_local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.domain.models.Favorite
import com.example.domain.models.Film
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import java.io.IOException

open class BaseTest {

    private lateinit var database: CharactersDatabase
    protected lateinit var dao: FavoritesDao

    @Before
    open fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, CharactersDatabase::class.java)
            .build()

        dao = database.favoritesDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        runBlocking(Dispatchers.IO) {
            database.clearAllTables()
        }
        database.close()
    }

    val favorite = Favorite(
        name = "Hans",
        birthYear = "12 BBY",
        height = "123",
        planetName = "Tatooine",
        planetPopulation = "1000",
        specieName = "Galactic",
        specieLanguage = "English",
        films = listOf(Film("title", "crawl"))
    )
}