package com.example.data_local.mappers

import com.example.data_local.models.CharacterEntity
import com.example.data_local.models.FilmEntity
import com.example.domain.models.Favorite
import com.example.domain.models.Film


internal fun Favorite.toEntity(): CharacterEntity {
    return CharacterEntity(
        name = name,
        birthYear = birthYear,
        height = height,
        planetName = planetName,
        planetPopulation = planetPopulation,
        specieLanguage = specieLanguage,
        specieName = specieName
    )
}

internal fun Film.toEntity(favId: Long): FilmEntity = FilmEntity(title, openingCrawl, favId)

