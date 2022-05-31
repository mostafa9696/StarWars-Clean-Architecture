package com.example.data_local.mappers

import com.example.data_local.models.FavoriteWithFilms
import com.example.data_local.models.FilmEntity
import com.example.domain.models.Favorite
import com.example.domain.models.Film


internal fun FilmEntity.toDomain(): Film = Film(title, openingCrawl)

internal fun FavoriteWithFilms.toDomain(): Favorite =
    Favorite(
        name = characterEntity.name,
        birthYear = characterEntity.birthYear,
        height = characterEntity.height,
        planetName = characterEntity.planetName,
        planetPopulation = characterEntity.planetPopulation,
        specieLanguage = characterEntity.specieLanguage,
        specieName = characterEntity.specieName,
        films = filmEntities.map { it.toDomain() }
    )