package com.example.domain.repository

import com.example.domain.models.CharacterDetailsURLs
import com.example.domain.models.Film
import com.example.domain.models.Planet
import com.example.domain.models.Specie
import kotlinx.coroutines.flow.Flow


interface ICharacterDetailsRepository {

    suspend fun getCharacterURLsDetails(characterUrl: String): Flow<CharacterDetailsURLs>

    suspend fun getCharacterPlanet(homeworldUrl: String): Planet

    suspend fun getCharacterSpecies(specieUrls: List<String>): List<Specie>

    suspend fun getCharacterFilms(filmUrls: List<String>): List<Film>

}