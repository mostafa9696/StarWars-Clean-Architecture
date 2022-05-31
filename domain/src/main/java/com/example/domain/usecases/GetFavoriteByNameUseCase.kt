package com.example.domain.usecases

import com.example.domain.models.Favorite
import com.example.domain.repository.IFavoritesRepository
import kotlinx.coroutines.flow.Flow


class GetFavoriteByNameUseCase(
    private val favoritesRepository: IFavoritesRepository
) : BaseUseCase<String, Flow<Favorite?>> {

    override suspend fun invoke(params: String) = favoritesRepository.getFavoriteByName(params)

}