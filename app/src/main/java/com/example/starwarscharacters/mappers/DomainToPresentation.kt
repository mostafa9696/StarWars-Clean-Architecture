package com.example.starwarscharacters.mappers

import com.example.domain.models.*
import com.example.starwarscharacters.models.*

internal fun Character.toPresentation(): CharacterPresentation {
    return CharacterPresentation(
        name,
        birthYear,
        height,
        url
    )
}

fun Planet.toPresentation(): PlanetPresentation {
    return PlanetPresentation(name, populationToLong(population))
}

fun Film.toPresentation(): FilmPresentation {
    return FilmPresentation(title, openingCrawl)
}

fun Specie.toPresentation(): SpeciePresentation {
    return SpeciePresentation(name, language)
}

internal fun populationToLong(population: String): Long {
    return if (population.contains("unknown", ignoreCase = true)) 0L else population.toLong()
}

internal fun Favorite.toPresentation(): FavoritePresentation {
    val characterPresentation =
        CharacterPresentation(name, birthYear, height, "")
    val planetPresentation = PlanetPresentation(planetName, populationToLong(planetPopulation))
    val speciePresentation = SpeciePresentation(specieName, specieLanguage)
    return FavoritePresentation(
        characterPresentation = characterPresentation,
        planetPresentation = planetPresentation,
        speciePresentation = listOf(speciePresentation),
        films = films.map { it.toPresentation() }
    )
}