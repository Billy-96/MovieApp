package com.example.movieapp.Interfaces

import com.example.movieapp.model.Movie

interface RepoImp {
    fun getComingMovies(): List<Movie>
    fun getPlayingMovies(): List<Movie>
    fun getAllMovies():List<Movie>
}