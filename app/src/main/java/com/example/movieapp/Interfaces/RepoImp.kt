package com.example.movieapp.Interfaces

import com.example.movieapp.model.MovieData
import retrofit2.Call
import retrofit2.Response

interface RepoImp {
    suspend fun getPlayingMoviesFromDB(
        api_key:String,language:String,sort_by:String,page:Int
    ): Response<MovieData>

    suspend fun getRatedMoviesFromDB(
        api_key: String,language: String,sort_by: String,page: Int
    ):Response<MovieData>
}