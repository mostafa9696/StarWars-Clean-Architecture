package com.example.starwarscharacters.mappers

import com.example.domain.models.Character
import com.example.domain.models.Film
import com.example.domain.models.Planet
import com.example.domain.models.Specie
import com.example.starwarscharacters.models.CharacterPresentation
import com.example.starwarscharacters.models.FilmPresentation
import com.example.starwarscharacters.models.PlanetPresentation
import com.example.starwarscharacters.models.SpeciePresentation

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