package com.example.data_local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    @ColumnInfo(name = "birth_year") val birthYear: String,
    val height: String,
    @ColumnInfo(name = "planet_name") val planetName: String,
    @ColumnInfo(name = "planet_population") val planetPopulation: String,
    @ColumnInfo(name = "specie_name") val specieName: String,
    @ColumnInfo(name = "specie_language") val specieLanguage: String
)