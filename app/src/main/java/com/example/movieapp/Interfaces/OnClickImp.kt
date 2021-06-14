package com.example.movieapp.Interfaces

import com.example.movieapp.model.Movie

interface OnClickImp {
    fun onCardClick(position: Int,list:List<Movie>)
    fun onFavClick(position: Int,list:List<Movie>)
}