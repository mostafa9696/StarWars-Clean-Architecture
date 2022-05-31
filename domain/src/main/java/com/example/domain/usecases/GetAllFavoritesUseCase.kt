package com.example.domain.usecases

import com.example.domain.models.Favorite
import com.example.domain.repository.IFavoritesRepository
import kotlinx.coroutines.flow.Flow


class GetAllFavoritesUseCase(
    private val favoritesRepository: IFavoritesRepository
) : BaseUseCase<Unit, Flow<List<Favorite>>> {

    override suspend fun invoke(params: Unit) = favoritesRepository.getAllFavorites()

}