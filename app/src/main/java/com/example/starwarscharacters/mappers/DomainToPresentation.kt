package com.example.starwarscharacters.mappers

import com.example.domain.models.Character
import com.example.starwarscharacters.models.CharacterPresentation

internal fun Character.toPresentation(): CharacterPresentation {
    return CharacterPresentation(
        name,
        birthYear,
        height,
        url
    )
}