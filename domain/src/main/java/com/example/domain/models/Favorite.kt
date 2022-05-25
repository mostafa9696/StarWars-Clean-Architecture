package com.example.domain.models

data class Favorite(
    val name: String,
    val birthYear: String,
    val height: String,
    val planetName: String,
    val planetPopulation: String,
    val specieName: String,
    val specieLanguage: String,
    val films: List<Film>
)