package com.example.starwarscharacters.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmPresentation(val title: String, val openingCrawl: String): Parcelable
