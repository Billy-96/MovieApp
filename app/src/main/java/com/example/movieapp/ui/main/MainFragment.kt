package com.example.movieapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.Adapters.AdapterMain
import com.example.movieapp.Interfaces.OnClickImp
import com.example.movieapp.R
import com.example.movieapp.Utils.Util
import com.example.movieapp.databinding.MainFragmentBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.ui.main.Fragments.DescriptionFragment

class MainFragment : Fragment(), OnClickImp {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
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
        viewModel.getPlayingMovies()

        viewModel.liveDataComing.observe(viewLifecycleOwner, {
            showComingList(it.results)
        })

        viewModel.liveDataPlaying.observe(viewLifecycleOwner, {
            showPlayingList(it.results)
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun showComingList(list: List<Movie>) {

    }

    private fun showPlayingList(list: List<Movie>) {
        binding.movieListPopular.layoutManager = LinearLayoutManager(view?.context,
            LinearLayoutManager.HORIZONTAL, false)
        binding.movieListPopular.adapter = AdapterMain(context, list, this)
    }

    override fun onCardClick(position: Int,list: List<Movie>) {
        val bundle = Bundle()
        bundle.putSerializable(Util.KEY, list.get(position))
        val fragment = DescriptionFragment()
        fragment.arguments = bundle
        setFragment(fragment)
    }

    override fun onFavClick(position: Int,list: List<Movie>) {
        viewModel.favClicked(position)
    }
    private fun setFragment(fragment: Fragment){
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }
}