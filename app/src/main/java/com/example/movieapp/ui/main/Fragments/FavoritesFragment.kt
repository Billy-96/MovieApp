package com.example.movieapp.ui.main.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.Adapters.AdapterMain
import com.example.movieapp.Interfaces.OnClickImp
import com.example.movieapp.databinding.FragmentFavoritesBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieData
import com.example.movieapp.ui.main.MainViewModel

class FavoritesFragment : Fragment(), OnClickImp {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getPlayingMovies()

        recyclerView = binding.recycleFavorites

    }

    fun getList(list: List<Movie>) {
    }

    override fun onCardClick(position: Int, list: List<Movie>) {
        TODO("Not yet implemented")
    }

    override fun onFavClick(position: Int, list: List<Movie>) {
        TODO("Not yet implemented")
    }

}