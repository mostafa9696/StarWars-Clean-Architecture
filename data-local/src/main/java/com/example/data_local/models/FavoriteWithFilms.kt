package com.example.data_local.models

import androidx.room.Embedded
import androidx.room.Relation


data class FavoriteWithFilms(
    @Embedded val characterEntity: CharacterEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "character_id"
    )
    val filmEntities: List<FilmEntity>
)