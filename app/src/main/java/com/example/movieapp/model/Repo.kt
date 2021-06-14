package com.example.movieapp.model

import com.example.movieapp.Interfaces.RepoImp
import com.example.movieapp.Network.NetworkUtils
import com.example.movieapp.R
import retrofit2.Call
import java.util.*

class Repo(val networkUtils: NetworkUtils) : RepoImp {

    override fun getPlayingMoviesFromDB(api_key: String, language: String): Call<MovieData> {
        return networkUtils.getListPlaying(api_key, language)
    }
}