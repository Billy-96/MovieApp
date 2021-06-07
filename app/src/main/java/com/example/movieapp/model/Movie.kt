package com.example.movieapp.model

import android.graphics.drawable.Drawable
import java.io.Serializable
import java.util.*

data class Movie(
    val title: String,
    val desc: String,
    val poster: Int,
    val comingDate: Date,
    val rating: Double,
    var fav: Boolean = false
):Serializable