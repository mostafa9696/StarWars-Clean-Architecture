package com.example.starwarscharacters.di

import com.example.domain.usecases.*
import org.koin.dsl.module

val useCasesModule = module {
    single { SearchUseCase(get()) }

    single { CharacterURLsDetailsUseCase(get()) }

    single { GetPlanetUseCase(get()) }

    single { GetSpeciesUseCase(get()) }

    single { GetFilmsUseCase(get()) }

    single { GetAllFavoritesUseCase(get()) }

    single { InsertFavoriteUseCase(get()) }

    single { DeleteFavoriteByNameUseCase(get()) }

    single { GetFavoriteByNameUseCase(get()) }

}