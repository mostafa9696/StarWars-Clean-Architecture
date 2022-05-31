package com.example.domain.usecases

import com.example.domain.models.Favorite
import com.example.domain.models.Result
import com.example.domain.repository.IFavoritesRepository
import kotlinx.coroutines.flow.Flow


class InsertFavoriteUseCase(
    private val favoritesRepository: IFavoritesRepository
) : BaseUseCase<Favorite, Flow<Result>> {

    override suspend fun invoke(params: Favorite) = favoritesRepository.insertFavorite(params)

}