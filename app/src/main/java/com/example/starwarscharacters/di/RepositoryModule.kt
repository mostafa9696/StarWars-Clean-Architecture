package com.example.starwarscharacters.di

import com.example.data_remote.repository.CharacterSearchRepository
import com.example.domain.repository.ICharacterSearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<ICharacterSearchRepository> { CharacterSearchRepository(get()) }
}