package com.example.domain.repository

import com.example.domain.models.Favorite
import com.example.domain.models.Result
import kotlinx.coroutines.flow.Flow

interface IFavoritesRepository {

    fun getAllFavorites(): Flow<List<Favorite>>

    fun getFavoriteByName(name: String): Flow<Favorite?>

    fun deleteFavoriteByName(name: String): Flow<Int>

    fun deleteAllFavorites(): Flow<Int>

    fun insertFavorite(favorite: Favorite): Flow<Result>

}