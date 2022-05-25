package com.example.starwarscharacters.di

import com.example.starwarscharacters.viewmodel.CharacterDetailsViewModel
import com.example.starwarscharacters.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        CharactersViewModel(
            searchUseCase = get()
        )
    }
    viewModel {
        CharacterDetailsViewModel(
            get(),
            get(),
            get(),
            get()
        )
    }
}