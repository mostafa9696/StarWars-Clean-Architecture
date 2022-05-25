package com.example.data_remote.api

import com.example.data_remote.models.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsAPIService {

    @GET("people/")
    suspend fun searchCharacters(@Query("search") searchQuery: String): SearchResponse

    @GET
    suspend fun getCharacterURLsDetails(@Url characterUrl: String): CharacterDetailsURLsResponse

    @GET
    suspend fun getSpecieDetails(@Url speciesUrl: String): SpecieDetailResponse

    @GET
    suspend fun getFilmDetails(@Url filmsUrl: String): FilmDetailResponse

    @GET
    suspend fun getPlanetDetails(@Url planetUrl: String): PlanetDetailsResponse

}