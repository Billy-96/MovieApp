package com.example.movieapp.ui.main.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieapp.Utils.Util
import com.example.movieapp.databinding.FragmentDescriptionBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieData
import com.example.movieapp.ui.main.MainViewModel

class DescriptionFragment : Fragment() {
    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDescriptionBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val bundle = this.arguments
        val movie = bundle?.getSerializable(Util.KEY) as Movie

        context?.let {
            Glide.with(it).load(Util.IMAGE_URL + movie.poster_path).into(binding.posterDesc)
        }
        binding.titleDesc.text = movie.title
        binding.descDesc.text = movie.overview
        binding.rateDesc.text = movie.vote_average.toString()
        binding.dateDesc.text = movie.release_date
    }
}