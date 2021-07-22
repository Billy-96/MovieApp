package com.example.movieapp.ui.main.Fragments

import android.app.ActionBar
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.Utils.Util
import com.example.movieapp.databinding.FragmentDescriptionBinding
import com.example.movieapp.model.LinkVideos
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieData
import com.example.movieapp.room.FavoriteViewModel
import com.example.movieapp.ui.main.MainViewModel
import kotlinx.coroutines.InternalCoroutinesApi

class DescriptionFragment : Fragment() {
    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var favViewModel: FavoriteViewModel
    lateinit var list: MutableList<LinkVideos>

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
        favViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        list = mutableListOf()

        val bundle = this.arguments
        val movie = bundle?.getSerializable(Util.KEY) as Movie

        (activity as AppCompatActivity).supportActionBar?.title = "Description"

        context?.let {
            Glide.with(it).load(Util.IMAGE_URL + movie.poster_path).into(binding.posterDesc)
        }
        binding.titleDesc.text = movie.title
        binding.descDesc.text = movie.overview
        binding.rateDesc.text = movie.vote_average.toString()
        binding.dateDesc.text = movie.release_date

        var linkKey = ""
        viewModel.getVideos(movie.id)

        viewModel.liveDataForCurrentVideos.observe(viewLifecycleOwner) {
            list.addAll(it.results)

            for (i in list) {
                binding.Link.text = "Смотреть трейлер"
                linkKey = i.key
            }
        }

        binding.Link.setOnClickListener {
            Toast.makeText(context, "link", Toast.LENGTH_SHORT).show()
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://watch?v=$linkKey"))
            startActivity(intent)
        }

        if (movie.favorite){

            binding.favImageButton.setImageResource(R.drawable.ic_baseline_favorite_24)

            binding.favImageButton.setOnClickListener {
                movie.favorite = false
                favViewModel.delMovie(movie)
                binding.favImageButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }else{

            binding.favImageButton.setOnClickListener {
                movie.favorite = true
                favViewModel.addMovie(movie)
                binding.favImageButton.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
        }


    }
}