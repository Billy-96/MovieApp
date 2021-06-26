package com.example.movieapp.ui.main

import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.Network.NetworkUtils
import com.example.movieapp.Utils.Util
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieData
import com.example.movieapp.model.Repo
import com.example.movieapp.ui.main.Fragments.DescriptionFragment
import com.google.android.material.theme.overlay.MaterialThemeOverlay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class MainViewModel : ViewModel() {
    val liveDataPlaying = MutableLiveData<MovieData>()
    val liveDataRated = MutableLiveData<MovieData>()
    private val repository = Repo()

    init {
        getPlayingMovies()
        getRatedMovies()
    }

    private fun getPlayingMovies() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    repository.getPlayingMoviesFromDB(Util.API_KEY, "ru", "popularity.desc", 1)
                if (response.isSuccessful) {
                    val movies = response.body()
                    Log.i("MYTAG", movies.toString())
                    withContext(Dispatchers.Main) {
                        liveDataPlaying.value = movies!!
                    }
                }
            } catch (e: HttpException) {
                Log.i("MYTAG", e.message().toString())
            } catch (t: Throwable) {
                Log.i("MYTAG", t.message.toString())
            }
        }
    }

    private fun getRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    repository.getRatedMoviesFromDB(Util.API_KEY, "ru", "vote_count.desc", 1)
                if (response.isSuccessful) {
                    val movies = response.body()
                    withContext(Dispatchers.Main) {
                        liveDataRated.value = movies!!
                    }
                }
            } catch (e: HttpException) {
                Log.i("MYTAG", e.message().toString())
            } catch (t: Throwable) {
                Log.i("MYTAG", t.message.toString())
            }
        }
    }

    fun getNewMovies(sort: String, page: Int, data: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    repository.getRatedMoviesFromDB(Util.API_KEY, "ru", sort, page)
                if (response.isSuccessful) {
                    val movies = response.body()
                    withContext(Dispatchers.Main) {
                        if (data == 1) {
                            liveDataPlaying.value = movies!!
                        } else {
                            liveDataRated.value = movies!!
                        }
                    }
                }
            } catch (e: HttpException) {
                Log.i("MYTAG", e.message().toString())
            } catch (t: Throwable) {
                val i = Log.i("MYTAG", t.message.toString())
            }
        }
    }

    fun favClicked(position: Int) {

    }

}