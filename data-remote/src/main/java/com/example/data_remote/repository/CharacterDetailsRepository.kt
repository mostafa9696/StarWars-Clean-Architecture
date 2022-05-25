package com.example.data_remote.repository

import com.example.data_remote.api.StarWarsAPIService
import com.example.data_remote.enforceHttps
import com.example.data_remote.mappers.toDomain
import com.example.domain.models.CharacterDetailsURLs
import com.example.domain.models.Film
import com.example.domain.models.Planet
import com.example.domain.models.Specie
import com.example.domain.repository.ICharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterDetailsRepository(
    private val apiService: StarWarsAPIService
) : ICharacterDetailsRepository {
    override suspend fun getCharacterURLsDetails(characterUrl: String): Flow<CharacterDetailsURLs> =
        flow {
            val characterURLsDetails =
                apiService.getCharacterURLsDetails(characterUrl.enforceHttps())
            emit(characterURLsDetails.toDomain())
        }

    override suspend fun getCharacterPlanet(homeworldUrl: String): Planet {
        val planet = apiService.getPlanetDetails(homeworldUrl.enforceHttps())
        return planet.toDomain()
    }

    override suspend fun getCharacterSpecies(specieUrls: List<String>): List<Specie> {
        val species = mutableListOf<Specie>()
        for (specieUrl in specieUrls) {
            val specie = apiService.getSpecieDetails(specieUrl.enforceHttps())
            species.add(specie.toDomain())
        }
        return species
    }

    override suspend fun getCharacterFilms(filmUrls: List<String>): List<Film> {
        val films = mutableListOf<Film>()
        for (filmUrl in filmUrls) {
            val film = apiService.getFilmDetails(filmUrl.enforceHttps())
            films.add(film.toDomain())
        }
        return films
    }
}