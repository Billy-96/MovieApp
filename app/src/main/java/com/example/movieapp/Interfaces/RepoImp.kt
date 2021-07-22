package com.example.movieapp.Interfaces

import com.example.movieapp.model.MovieData
import com.example.movieapp.model.Videos
import retrofit2.Call
import retrofit2.Response

interface RepoImp {
    suspend fun getPlayingMoviesFromDB(
        api_key: String, language: String, sort_by: String, page: Int
    ): Response<MovieData>

    suspend fun getRatedMoviesFromDB(
        api_key: String, language: String, sort_by: String, page: Int
    ): Response<MovieData>

    suspend fun getCurrentVideos(
        id: Int, api_key: String, language: String
    ): Response<Videos>
}