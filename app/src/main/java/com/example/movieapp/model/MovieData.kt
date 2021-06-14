package com.example.movieapp.model

import android.graphics.drawable.Drawable
import retrofit2.Call
import java.io.Serializable
import java.util.*

data class MovieData(
    val results : List<Movie>
):Serializable