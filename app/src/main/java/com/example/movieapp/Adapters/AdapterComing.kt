package com.example.movieapp.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.Interfaces.OnClickImp
import com.example.movieapp.R
import com.example.movieapp.model.Movie
import com.example.movieapp.ui.main.MainViewModel

class AdapterComing(
    val context: Context?,
    private val listOfMovies: List<Movie>,
    val listener: OnClickImp
) : RecyclerView.Adapter<AdapterComing.Holder>() {
    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        val card = itemView.findViewById<CardView>(R.id.card_coming)
        val image = itemView.findViewById<ImageView>(R.id.image_coming)
        val title = itemView.findViewById<TextView>(R.id.title_coming)
        val comingDate = itemView.findViewById<TextView>(R.id.date_coming)
//        init{card.setOnClickListener(this)}
        override fun onClick(v: View?) {
            when(v?.id){
                R.id.card_coming -> listener.onCardClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.maket_for_upcoming, parent, false)
        val holder = Holder(view)
        return holder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val movie = listOfMovies.get(position)
        context?.let { Glide.with(it).load(movie.poster).into(holder.image) }
        holder.title.text = movie.title
        val year = movie.comingDate.year.toString()
        val month = movie.comingDate.month.toString()
        val day = movie.comingDate.day.toString()
        holder.comingDate.text = "$day.$month.$year "
    }

    override fun getItemCount(): Int {
        return listOfMovies.size
    }


}