package com.example.starwarscharacters.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SpeciePresentation(val name: String, val language: String): Parcelable
