package com.example.movieapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.movieapp.MainActivity
import com.example.movieapp.R
import com.example.movieapp.Utils.Util
import com.example.movieapp.model.Movie
import com.example.movieapp.model.Repo
import com.example.movieapp.ui.main.Fragments.DescriptionFragment
import com.example.movieapp.ui.main.MainFragment.Companion.newInstance
import java.lang.reflect.Array.newInstance

class MainViewModel : ViewModel() {
    private val liveDataPlaying = MutableLiveData<List<Movie>>()
    private val liveDataComing = MutableLiveData<List<Movie>>()
    private val descriptionFragment = DescriptionFragment()
    val repository = Repo()

    fun getPlayingMovies() {
        val playingMovies = repository.getPlayingMovies()
        liveDataPlaying.value = playingMovies
    }

    fun getComingMovies() {
        val comingMovies = repository.getComingMovies()
        liveDataComing.value = comingMovies
    }

    fun getlistPlaying(): MutableLiveData<List<Movie>> {
        return liveDataPlaying
    }

    fun getListComing(): MutableLiveData<List<Movie>> {
        return liveDataComing
    }

    fun cardClicked(position: Int, fragmentManager: FragmentManager) {
        var bundle = Bundle()
        bundle.putSerializable(Util.key, repository.getPlayingMovies().get(position))
        descriptionFragment.arguments = bundle

        fragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container, descriptionFragment)
            .commit()
    }

    fun favClicked(position: Int) {
        if (repository.getPlayingMovies().get(position).fav){
            repository.getPlayingMovies().get(position).fav = false
        }else {
            repository.getPlayingMovies().get(position).fav = true
        }
    }
    fun setFragment(fragmentManager: FragmentManager,fragment:Fragment){
        fragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.container,fragment)
            .commit()
    }
}