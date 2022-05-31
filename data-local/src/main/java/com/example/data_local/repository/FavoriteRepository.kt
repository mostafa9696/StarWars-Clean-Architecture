package com.example.data_local.repository

import com.example.data_local.FavoritesDao
import com.example.data_local.mappers.toDomain
import com.example.domain.models.Favorite
import com.example.domain.models.Result
import com.example.domain.repository.IFavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoriteRepository(private val favoritesDao: FavoritesDao): IFavoritesRepository {

    override fun getAllFavorites(): Flow<List<Favorite>> = flow {
        val favs = favoritesDao.getAll().map { it.toDomain() }
        emit(favs)
    }

    override fun getFavoriteByName(name: String): Flow<Favorite?> = flow {
        val fav = favoritesDao.getByName(name)
        //If value not in table will be null
        if (fav != null)
            emit(fav.toDomain())
        else
            emit(null)
    }

    override fun deleteFavoriteByName(name: String): Flow<Int> = flow {
        val rowsAffected = favoritesDao.deleteByName(name)
        emit(rowsAffected)
    }

    override fun deleteAllFavorites(): Flow<Int> = flow {
        val rowsAffected = favoritesDao.deleteAll()
        emit(rowsAffected)
    }

    override fun insertFavorite(favorite: Favorite): Flow<Result> = flow {
        val result = favoritesDao.insert(favorite)
        emit(result)
    }
}