package com.example.starwarscharacters.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlanetPresentation(val name: String, val population: Long): Parcelable