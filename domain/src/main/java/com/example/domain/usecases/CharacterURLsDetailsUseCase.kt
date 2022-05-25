package com.example.domain.usecases

import com.example.domain.models.CharacterDetailsURLs
import com.example.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow

class CharacterURLsDetailsUseCase (
    private val characterRepo: ICharacterDetailsRepository
): BaseUseCase<String, Flow<CharacterDetailsURLs>> {

    override suspend fun invoke(params: String): Flow<CharacterDetailsURLs> =
        characterRepo.getCharacterURLsDetails(params)
}