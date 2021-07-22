package com.example.movieapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity
data class Movie(
    val poster_path: String,
    val overview: String,
    val release_date: String,
    val original_title: String,
    val title: String,
    val backdrop_path: String,
    val popularity: Double,
    val vote_average: Double,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var favorite: Boolean = false
) : Serializable


