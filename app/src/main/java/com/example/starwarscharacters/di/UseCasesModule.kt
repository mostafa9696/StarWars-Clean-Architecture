package com.example.starwarscharacters.di

import com.example.domain.usecases.SearchUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { SearchUseCase(get()) }
}