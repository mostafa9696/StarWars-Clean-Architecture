package com.example.data_remote.models

import com.squareup.moshi.Json

data class CharacterResponse (
    val name: String,
    @field:Json(name = "birth_year") val birthYear: String,
    val height: String,
    val url: String
)