package com.example.data_remote.models

import com.squareup.moshi.Json


data class FilmDetailResponse(
    val title:String,
    @field:Json(name = "opening_crawl") val openingCrawl: String
)