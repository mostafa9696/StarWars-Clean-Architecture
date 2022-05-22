package com.example.data_remote.mappers

import com.example.data_remote.models.CharacterResponse
import com.example.domain.models.Character

internal fun CharacterResponse.toDomain(): Character {
    return Character(this.name, this.birthYear, this.height, this.url)
}