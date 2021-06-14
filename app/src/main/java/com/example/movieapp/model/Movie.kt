package com.example.movieapp.model

import java.io.Serializable

data class Movie(
    val poster_path: String,
    val overview:String,
    val release_date:String,
    val original_title:String,
    val title:String,
    val backdrop_path:String,
    val popularity:Double,
    val vote_average : Double
):Serializable
