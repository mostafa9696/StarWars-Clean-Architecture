package com.example.domain.usecases

import com.example.domain.models.Character
import com.example.domain.repository.ICharacterSearchRepository
import kotlinx.coroutines.flow.Flow

class SearchUseCase(
    private val searchRepository: ICharacterSearchRepository
): BaseUseCase<String, Flow<List<Character>>> {

    override suspend fun invoke(params: String): Flow<List<Character>> =
        searchRepository.searchCharacters(params)
}