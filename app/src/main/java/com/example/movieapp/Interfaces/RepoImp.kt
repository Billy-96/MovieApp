package com.example.movieapp.Interfaces

import com.example.movieapp.model.MovieData
import retrofit2.Call

interface RepoImp {
    suspend fun getPlayingMoviesFromDB(
        api_key:String,language:String,sort_by:String
    ): Call<MovieData>

    suspend fun getRatedMoviesFromDB(
        api_key: String,language: String,sort_by: String
    ):Call<MovieData>
}