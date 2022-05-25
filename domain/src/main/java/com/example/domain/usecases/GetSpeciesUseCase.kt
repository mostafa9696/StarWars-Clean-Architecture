package com.example.domain.usecases

import com.example.domain.models.Specie
import com.example.domain.repository.ICharacterDetailsRepository

class GetSpeciesUseCase(
    private val characterDetailsRepository: ICharacterDetailsRepository
) : BaseUseCase<List<String>, List<Specie>>  {

    override suspend operator fun invoke(params: List<String>,) =
        characterDetailsRepository.getCharacterSpecies(params)

}