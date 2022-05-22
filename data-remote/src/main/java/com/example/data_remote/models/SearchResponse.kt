package com.example.data_remote.models

data class SearchResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<CharacterResponse>
)