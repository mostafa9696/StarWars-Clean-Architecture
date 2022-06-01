package com.example.data_local

import androidx.room.*
import com.example.data_local.mappers.toEntity
import com.example.data_local.models.CharacterEntity
import com.example.data_local.models.FavoriteWithFilms
import com.example.data_local.models.FilmEntity
import com.example.domain.models.Favorite
import com.example.domain.models.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("DELETE FROM character")
    suspend fun deleteAll(): Int

    @Query("DELETE FROM character WHERE name=:name")
    suspend fun deleteByName(name: String): Int

    @Transaction
    @Query("SELECT * FROM character WHERE name=:name")
    suspend fun getByName(name: String): FavoriteWithFilms

    @Transaction
    @Query("SELECT * FROM character")
    fun getAll(): Flow<List<FavoriteWithFilms>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterEntity: CharacterEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filmEntity: FilmEntity): Long

    @Transaction
    suspend fun insert(favorite: Favorite):Result {
        val favId = insert(favorite.toEntity())
        for (film in favorite.films) {
            val filmEntity = film.toEntity(favId)
            insert(filmEntity)
            return Result.SUCCESS
        }
        return Result.FAILURE
    }

}