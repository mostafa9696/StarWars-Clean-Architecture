package com.example.starwarscharacters.utils

import com.example.domain.models.*

object Data {
    val films = listOf(Film(title = "title", openingCrawl = "opening crawl"))
    val planet = Planet(name = "name", population = "100000")
    val species = listOf(Specie(name = "name", language = "language"))
    val characters = listOf(
        Character(
            "Darth Vader",
            "12BBY",
            "123",
            "/api/species/2/"
        )
    )

    const val CHARACTER_URL = "/api/people/1/"
    const val PLANET_URL = "https://swapi.dev/api/planets/1/"
    val FILMS_URLS = listOf("https://swapi.dev/api/films/1/")
    val SPECIES_URLS = listOf("https://swapi.dev/api/species/1/")
    const val SEARCH_PARAM = "Darth"

    val characterDetailsURLs = CharacterDetailsURLs(
        listOf("https://swapi.dev/api/films/1/"),
        "https://swapi.dev/api/planets/1/",
        listOf("https://swapi.dev/api/species/1/")
    )

}