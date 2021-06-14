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
    val liveDataComing = MutableLiveData<MovieData>()
    private val repository = Repo(NetworkUtils.getIntance())

    fun getPlayingMovies() {
        repository.getPlayingMoviesFromDB(Util.API_KEY,"ru")
            .enqueue(object : Callback<MovieData> {
                override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                    if(response.isSuccessful){
                        val movies= response.body()
                        liveDataPlaying.value = movies!!
                        Log.i("MyTag",movies.toString())
                    }
                }

                override fun onFailure(call: Call<MovieData>, t: Throwable) {
                    Log.i("MyTag",t.message.toString())
                }
            })
    }


    fun getlistPlaying(): MutableLiveData<MovieData> {
        return liveDataPlaying
    }

    fun getListComing(): MutableLiveData<MovieData> {
        return liveDataComing
    }


    fun favClicked(position: Int) {

    }
}