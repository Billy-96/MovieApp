package com.example.movieapp.model

import com.example.movieapp.Interfaces.RepoImp
import com.example.movieapp.R
import java.util.*

class Repo : RepoImp {
   private var listCome = mutableListOf<Movie>()
    private var listPlaying = mutableListOf<Movie>()
     init {
         setComingMovies()
         setPlayingMovies()
     }
    private fun setComingMovies(){
        listCome.add(
            Movie(
                "Joker", "Description", R.drawable.joker,
                Date(2020,2,21), 8.5
            )
        )
        listCome.add(
            Movie(
                "Avengers", "Description", R.drawable.avengers,
                Date(2019,5,12), 7.9
            )
        )
        listCome.add(
            Movie(
                "Beauty and The Beast", "Description", R.drawable.beauty_and_beast,
                Date(2018,7,13), 6.7
            )
        )
        listCome.add(
            Movie(
                "Black Mirror", "Description", R.drawable.blackmirror,
                Date(2020,9,1), 9.8
            )
        )
        listCome.add(
            Movie(
                "Avengers: Civil War", "Description", R.drawable.civil_war,
                Date(2018,3,21), 7.2
            )
        )
    }
    private fun setPlayingMovies(){
        listPlaying.add(
            Movie(
                "Daragons", "Description", R.drawable.dragons,
                Date(2017,12,19), 6.8,
            )
        )
        listPlaying.add(
            Movie(
                "Guardians of Galaxy", "Description", R.drawable.guardians,
                Date(2018,2,28), 6.3,
            )
        )
        listPlaying.add(
            Movie(
                "Mulan", "Description", R.drawable.mulan,
                Date(2019,7,14), 8.2
            )
        )
        listPlaying.add(
            Movie(
                "Spder-Man", "Description", R.drawable.spiderman,
                Date(2016,10,27), 8.8
            )
        )
        listPlaying.add(
            Movie(
                "Virus", "Description", R.drawable.virus,
                Date(2016,11,21), 7.9
            )
        )
    }

    override fun getComingMovies(): List<Movie> {
        return listCome
    }

    override fun getPlayingMovies(): List<Movie> {
        return listPlaying
    }

    override fun getAllMovies(): List<Movie> {
        var list = getPlayingMovies() + getComingMovies()
        return list
    }
}