package com.example.movieapp.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.Interfaces.OnClickImp
import com.example.movieapp.R
import com.example.movieapp.Utils.Util
import com.example.movieapp.databinding.MaketForPlayingBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieData

class AdapterMain(
    private val context: Context?,
    private var listOfMovies: List<Movie>,
    val listener: OnClickImp
) : RecyclerView.Adapter<AdapterMain.Holder>() {
    inner class Holder(val binding: MaketForPlayingBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.apply {
                root.setOnClickListener {
                    listener.onCardClick(adapterPosition, listOfMovies)
                }
            }
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.parent -> listener.onCardClick(adapterPosition, listOfMovies)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = MaketForPlayingBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val movie = listOfMovies[position]
        holder.binding.movieTitleItem.text = movie.title
        holder.binding.movieRatingItem.text = movie.vote_average.toString()
        holder.binding.movieReleaseItem.text = movie.release_date

        context?.let {
            Glide.with(it).load(Util.IMAGE_URL + movie.poster_path)
                .into(holder.binding.moviePosterItem)
        }
    }

    override fun getItemCount(): Int {
        return listOfMovies.size
    }
}