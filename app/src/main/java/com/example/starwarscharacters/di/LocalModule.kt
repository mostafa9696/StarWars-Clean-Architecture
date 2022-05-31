package com.example.starwarscharacters.di

import androidx.room.Room
import com.example.data_local.CharactersDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {

    single {
        Room.databaseBuilder(androidContext(), CharactersDatabase::class.java, "character_db").build()
    }

    single {
        getFavoriteDao(database = get ())
    }


}

fun getFavoriteDao(database: CharactersDatabase) = database.favoritesDao()