package com.example.movieapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.Adapters.AdapterComing
import com.example.movieapp.Adapters.AdapterMain
import com.example.movieapp.Interfaces.OnClickImp
import com.example.movieapp.R
import com.example.movieapp.databinding.MainFragmentBinding
import com.example.movieapp.model.Movie

class MainFragment : Fragment(), OnClickImp {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerViewPlaying: RecyclerView
    lateinit var adapterComing: AdapterComing
    lateinit var adapterMain: AdapterMain
    lateinit var recyclerViewComing: RecyclerView

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getComingMovies()
        viewModel.getPlayingMovies()

        recyclerViewComing = binding.recycleComing
        recyclerViewPlaying = binding.recyclerPlaying

        viewModel.getListComing().observe(viewLifecycleOwner, {
            showComingList(it)
        })
        viewModel.getlistPlaying().observe(viewLifecycleOwner, {
            showPlayingList(it)
        })


    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    fun showComingList(list: List<Movie>) {
        recyclerViewComing.layoutManager = LinearLayoutManager(
            view?.context,
            LinearLayoutManager.HORIZONTAL, false
        )
        adapterComing = AdapterComing(context, list, this)
        recyclerViewComing.adapter = adapterComing
    }

    fun showPlayingList(list: List<Movie>) {
        recyclerViewPlaying.layoutManager = LinearLayoutManager(
            view?.context,
            LinearLayoutManager.HORIZONTAL, false
        )
        adapterMain = AdapterMain(context, list, this)
        recyclerViewPlaying.adapter = adapterMain
    }

    override fun onCardClick(position: Int) {
        viewModel.cardClicked(position, parentFragmentManager)
    }

    override fun onFavClick(position: Int) {
        viewModel.favClicked(position)
    }
}