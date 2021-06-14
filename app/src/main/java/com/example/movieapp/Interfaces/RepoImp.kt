package com.example.movieapp.Interfaces

import com.example.movieapp.model.MovieData
import retrofit2.Call

interface RepoImp {
    fun getPlayingMoviesFromDB(
        api_key:String,language:String
    ): Call<MovieData>
}