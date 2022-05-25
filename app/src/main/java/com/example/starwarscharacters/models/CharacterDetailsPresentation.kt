package com.example.starwarscharacters.models


data class CharacterDetailsPresentation(
    val planet: PlanetPresentation,
    val specie: List<SpeciePresentation>,
    val filmPresentation: List<FilmPresentation>
)