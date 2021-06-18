package com.example.movieapp.model

import com.example.movieapp.Interfaces.RepoImp
import com.example.movieapp.Network.NetworkUtils
import retrofit2.Call

class Repo(val networkUtils: NetworkUtils) : RepoImp {

    override suspend fun getPlayingMoviesFromDB(
        api_key: String,
        language: String,
        sort_by: String
    ): Call<MovieData> {
        return networkUtils.getListFromNet(api_key, language, sort_by)
    }

    override suspend fun getRatedMoviesFromDB(
        api_key: String,
        language: String,
        sort_by: String
    ): Call<MovieData> {
        return networkUtils.getListFromNet(api_key,language,sort_by)
    }
}