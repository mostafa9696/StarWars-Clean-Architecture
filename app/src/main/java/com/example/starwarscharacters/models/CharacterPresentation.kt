package com.example.starwarscharacters.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterPresentation(
    val name: String,
    val birthYear: String,
    val heightInCm: String,
    val url: String
) : Parcelable