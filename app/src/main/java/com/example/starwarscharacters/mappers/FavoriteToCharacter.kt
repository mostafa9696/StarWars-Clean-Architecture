package com.example.starwarscharacters.mappers

import com.example.starwarscharacters.models.CharacterDetailsPresentation
import com.example.starwarscharacters.models.FavoritePresentation

internal fun FavoritePresentation.toCharacterPresentation(): CharacterDetailsPresentation {
    return CharacterDetailsPresentation(
        planet = planetPresentation,
        species = speciePresentation,
        films = films
    )
}