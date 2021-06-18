package com.example.movieapp.ui.main

import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.Network.NetworkUtils
import com.example.movieapp.Utils.Util
import com.example.movieapp.model.MovieData
import com.example.movieapp.model.Repo
import com.example.movieapp.ui.main.Fragments.DescriptionFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    val liveDataPlaying = MutableLiveData<MovieData>()
    val liveDataRated = MutableLiveData<MovieData>()
    private val repository = Repo(NetworkUtils.getIntance())

    suspend fun getPlayingMovies() {
        repository.getPlayingMoviesFromDB(Util.API_KEY, "ru", "popularity.desc")
            .enqueue(object : Callback<MovieData> {
                override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                    if (response.isSuccessful) {
                        val movies = response.body()
                        liveDataPlaying.value = movies!!
                    }
                }

                override fun onFailure(call: Call<MovieData>, t: Throwable) {
                    Log.i("MyTag", t.message.toString())
                }
            })
    }

    suspend fun getRatedMovies() {
        repository.getRatedMoviesFromDB(Util.API_KEY, "ru", "vote_count.desc")
            .enqueue(object : Callback<MovieData> {
                override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                    if (response.isSuccessful) {
                        val movies = response.body()
                        liveDataRated.value = movies!!
                    }
                }

                override fun onFailure(call: Call<MovieData>, t: Throwable) {
                    Log.i("MyTag", t.message.toString())
                }
            })
    }


    fun favClicked(position: Int) {

    }
}