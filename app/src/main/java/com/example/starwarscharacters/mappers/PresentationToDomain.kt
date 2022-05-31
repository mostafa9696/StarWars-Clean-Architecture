package com.example.starwarscharacters.mappers

import com.example.domain.models.Favorite
import com.example.domain.models.Film
import com.example.starwarscharacters.models.FavoritePresentation
import com.example.starwarscharacters.models.FilmPresentation

internal fun FavoritePresentation.toDomain(): Favorite {
    val population =
        if (planetPresentation.population == 0L) "Unknown" else planetPresentation.population.toString()
    return Favorite(
        characterPresentation.name,
        characterPresentation.birthYear,
        characterPresentation.heightInCm,
        planetPresentation.name,
        population,
        if (speciePresentation.isNotEmpty()) speciePresentation[0].name else "Unknown",
        if (speciePresentation.isNotEmpty()) speciePresentation[0].language else "Unknown",
        films.map { it.toDomain() })
}

internal fun FilmPresentation.toDomain(): Film {
    return Film(title, openingCrawl)
}