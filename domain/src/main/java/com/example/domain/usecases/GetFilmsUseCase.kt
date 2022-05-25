package com.example.domain.usecases

import com.example.domain.models.Film
import com.example.domain.repository.ICharacterDetailsRepository


class GetFilmsUseCase(
    private val characterDetailsRepository: ICharacterDetailsRepository
) : BaseUseCase<List<String>, List<Film>>  {

    override suspend operator fun invoke(params: List<String>) =
        characterDetailsRepository.getCharacterFilms(params)
}