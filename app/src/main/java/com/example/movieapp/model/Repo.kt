package com.example.movieapp.model

import com.example.movieapp.Interfaces.RepoImp
import com.example.movieapp.Network.NetworkUtils
import retrofit2.Response

class Repo() : RepoImp {

    override suspend fun getPlayingMoviesFromDB(
        api_key: String,
        language: String,
        sort_by: String,
        page: Int
    ): Response<MovieData> {
        return NetworkUtils.getIntance().getListFromNet(api_key, language, sort_by, page)
    }

    override suspend fun getRatedMoviesFromDB(
        api_key: String,
        language: String,
        sort_by: String,
        page: Int
    ): Response<MovieData> {
        return NetworkUtils.getIntance().getListFromNet(api_key, language, sort_by, page)
    }

    override suspend fun getCurrentVideos(
        id: Int,
        api_key: String,
        language: String
    ): Response<Videos> {
        return NetworkUtils.getIntance().getVideos(id, api_key, language)
    }
}