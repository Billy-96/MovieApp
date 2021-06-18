package com.example.movieapp.ui.main.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.Adapters.AdapterMain
import com.example.movieapp.Interfaces.OnClickImp
import com.example.movieapp.R
import com.example.movieapp.Utils.Util
import com.example.movieapp.databinding.FragmentRatingBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.ui.main.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.*

class RatingFragment : Fragment(), OnClickImp {
    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerView: RecyclerView
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
        GlobalScope.launch(Dispatchers.IO){
            viewModel.getRatedMovies()
        }


        recyclerView = binding.recycleRating

        viewModel.liveDataRated.observe(viewLifecycleOwner, {
            showList(it.results)
        })

    }

    fun showList(list: List<Movie>) {
        binding.recycleRating.layoutManager = GridLayoutManager(
            view?.context, 3,
            GridLayoutManager.VERTICAL, false
        )
        binding.recycleRating.adapter = AdapterMain(context, list, this)
    }

    override fun onCardClick(position: Int, list: List<Movie>) {
        val bundle = Bundle()
        bundle.putSerializable(Util.KEY, list.get(position))
        val fragment = DescriptionFragment()
        fragment.arguments = bundle
        setFragment(fragment)
    }

    override fun onFavClick(position: Int, list: List<Movie>) {

    }

    private fun setFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }

}