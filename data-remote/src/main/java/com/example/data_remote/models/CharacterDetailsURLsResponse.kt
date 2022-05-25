package com.example.data_remote.models

import com.squareup.moshi.Json

data class CharacterDetailsURLsResponse(
    @field:Json(name = "films")val filmUrls: List<String>,
    @field:Json(name = "homeworld") val homeworldUrl: String,
    @field:Json(name = "species") val specieUrls: List<String>
)