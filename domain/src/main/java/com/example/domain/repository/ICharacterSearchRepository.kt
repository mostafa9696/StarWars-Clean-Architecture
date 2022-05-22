package com.example.domain.repository

import com.example.domain.models.Character
import kotlinx.coroutines.flow.Flow

interface ICharacterSearchRepository {
    suspend fun searchCharacters(characterName: String): Flow<List<Character>>
}