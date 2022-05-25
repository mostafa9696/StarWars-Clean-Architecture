package com.example.data_remote.mappers

import com.example.data_remote.models.*
import com.example.domain.models.*

internal fun CharacterResponse.toDomain(): Character {
    return Character(this.name, this.birthYear, this.height, this.url)
}

internal fun PlanetDetailsResponse.toDomain(): Planet {
    return Planet(this.name, this.population)
}

internal fun SpecieDetailResponse.toDomain(): Specie {
    return Specie(this.name, this.language)
}

internal fun FilmDetailResponse.toDomain(): Film {
    return Film(this.title, this.openingCrawl)
}

internal fun CharacterDetailsURLsResponse.toDomain(): CharacterDetailsURLs {
    return CharacterDetailsURLs(this.filmUrls, this.homeworldUrl, this.specieUrls)
}