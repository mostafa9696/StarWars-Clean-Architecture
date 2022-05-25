package com.example.domain.usecases

import com.example.domain.models.Planet
import com.example.domain.repository.ICharacterDetailsRepository


class GetPlanetUseCase(
    private val characterDetailsRepository: ICharacterDetailsRepository
) : BaseUseCase<String, Planet>  {

    override suspend operator fun invoke(params: String) =
        characterDetailsRepository.getCharacterPlanet(params)
}