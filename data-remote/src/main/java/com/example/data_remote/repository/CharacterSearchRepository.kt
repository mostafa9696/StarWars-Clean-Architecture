package com.example.data_remote.repository

import com.example.data_remote.api.StarWarsAPIService
import com.example.data_remote.mappers.toDomain
import com.example.domain.models.Character
import com.example.domain.repository.ICharacterSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterSearchRepository(
    private val apiService: StarWarsAPIService
): ICharacterSearchRepository {

    override suspend fun searchCharacters(characterName: String): Flow<List<Character>> = flow {
        val searchResponse = apiService.searchCharacters(characterName)
        val starWarsCharacters = mutableListOf<Character>()
        starWarsCharacters.addAll(
            searchResponse.results.map { it.toDomain() }
        )
        emit(starWarsCharacters)
    }

}