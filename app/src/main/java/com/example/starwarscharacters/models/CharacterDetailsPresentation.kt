package com.example.starwarscharacters.models


data class CharacterDetailsPresentation(
    val planet: PlanetPresentation,
    val species: List<SpeciePresentation>,
    val films: List<FilmPresentation>
)