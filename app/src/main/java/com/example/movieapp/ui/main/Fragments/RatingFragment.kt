package com.example.movieapp.ui.main.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.Interfaces.OnClickImp
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentFavoritesBinding
import com.example.movieapp.databinding.FragmentRatingBinding
import com.example.movieapp.databinding.MainFragmentBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.ui.main.MainViewModel

class RatingFragment : Fragment(),OnClickImp {
    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerView:RecyclerView
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRatingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getComingMovies()
        viewModel.getPlayingMovies()

        recyclerView = binding.recycleRating

        viewModel.getlistPlaying().observe(viewLifecycleOwner,{
            var list = mutableListOf<Movie>()
            var i =0
            var j =1
            while (i!=it.size) {
                if (j > i) {
                    if (j != it.size) {

                    } else {
                        j = 0
                    }
                }
                j++
            }
        })

    }
    fun showList(list: List<Movie>){

    }

    override fun onCardClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onFavClick(position: Int) {
        TODO("Not yet implemented")
    }
}