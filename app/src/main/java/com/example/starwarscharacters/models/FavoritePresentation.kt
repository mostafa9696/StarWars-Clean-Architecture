package com.example.starwarscharacters.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoritePresentation(
    val characterPresentation: CharacterPresentation,
    val planetPresentation: PlanetPresentation,
    val speciePresentation: List<SpeciePresentation>,
    val films: List<FilmPresentation>
) : Parcelable