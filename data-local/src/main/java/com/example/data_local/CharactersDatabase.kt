package com.example.data_local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data_local.models.CharacterEntity
import com.example.data_local.models.FilmEntity


@Database(entities = [CharacterEntity::class, FilmEntity::class], version = 1, exportSchema = false)
abstract class CharactersDatabase : RoomDatabase() {
    abstract fun favoritesDao(): FavoritesDao
}