package com.example.domain.usecases

import com.example.domain.repository.IFavoritesRepository
import kotlinx.coroutines.flow.Flow


class DeleteFavoriteByNameUseCase(
    private val favoritesRepository: IFavoritesRepository
) : BaseUseCase<String, Flow<Int>> {

    override suspend fun invoke(params: String) = favoritesRepository.deleteFavoriteByName(params)

}