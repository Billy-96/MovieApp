package com.example.movieapp.Adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.Interfaces.OnClickImp
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.example.movieapp.ui.main.MainViewModel
import kotlin.math.log

class AdapterMain(
    val context: Context?,
    private val listOfMovies: List<Movie>,
    val listener:OnClickImp
) : RecyclerView.Adapter<AdapterMain.Holder>(){
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        val card = itemView.findViewById<CardView>(R.id.card_playing)
        val image = itemView.findViewById<ImageView>(R.id.image_playing)
        val title = itemView.findViewById<TextView>(R.id.title_playing)
        val rating = itemView.findViewById<TextView>(R.id.rating_playing)
        val date = itemView.findViewById<TextView>(R.id.date_playing)
        val fav = itemView.findViewById<ImageView>(R.id.image_fav)

        init{
            card.setOnClickListener(this)
            fav.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            when(v?.id){
                R.id.card_playing -> listener.onCardClick(adapterPosition)
                R.id.image_fav -> {listener.onFavClick(adapterPosition)
                notifyDataSetChanged()}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.maket_for_playing, parent, false)
        val holder = Holder(view)
        return holder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val movie = listOfMovies.get(position)
        Log.i("MyTag",movie.fav.toString())
        if (movie.fav){
            holder.fav.setImageResource(R.drawable.ic_baseline_favorite_24)
        }else{
            holder.fav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
        context?.let { Glide.with(it).load(movie.poster).into(holder.image) }
        holder.title.text = movie.title
        holder.rating.text = movie.rating.toString()
        holder.date.text = movie.comingDate.year.toString()
    }

    override fun getItemCount(): Int {
        return listOfMovies.size
    }
}

