package com.example.data_remote.api

import com.example.data_remote.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsAPIService {

    @GET("people/")
    suspend fun searchCharacters(@Query("search") searchQuery: String): SearchResponse
}