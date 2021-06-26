package com.example.movieapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.movieapp.Adapters.AdapterMain
import com.example.movieapp.Interfaces.OnClickImp
import com.example.movieapp.R
import com.example.movieapp.Utils.Util
import com.example.movieapp.databinding.MainFragmentBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.ui.main.Fragments.DescriptionFragment
import kotlinx.coroutines.*

class MainFragment : Fragment(), OnClickImp {
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private var pageRated = 2
    private var pagePlaying = 2
    private var loading = true
    private var previousTotal = 0


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

        viewModel.liveDataRated.observe(viewLifecycleOwner, {
            showRatedList(it.results)
        })

        viewModel.liveDataPlaying.observe(viewLifecycleOwner, {
            showPlayingList(it.results)
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun showRatedList(list: List<Movie>) {
        loadMovies(binding.movieListRated,"vote_count.desc",2)
        binding.movieListRated.layoutManager = LinearLayoutManager(
            view?.context,
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.movieListRated.adapter =
            AdapterMain(context, list.sortedByDescending { it.vote_average }, this)
    }

    private fun showPlayingList(list: List<Movie>) {
        loadMovies(binding.movieListPopular,"popularity.desc",1)
        binding.movieListPopular.layoutManager = LinearLayoutManager(
            view?.context,
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.movieListPopular.adapter = AdapterMain(context, list, this)
    }

    override fun onCardClick(position: Int, list: List<Movie>) {
        val bundle = Bundle()
        bundle.putSerializable(Util.KEY, list.get(position))
        val fragment = DescriptionFragment()
        fragment.arguments = bundle
        setFragment(fragment)
    }

    override fun onFavClick(position: Int, list: List<Movie>) {
        viewModel.favClicked(position)
    }


    private fun setFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    fun loadMovies(recyclerView: RecyclerView, sort: String, data: Int) {
        recyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dx > 0) {
                    val visibleItemCount =
                        (recyclerView.layoutManager as LinearLayoutManager).childCount
                    val totalItemCount =
                        (recyclerView.layoutManager as LinearLayoutManager).itemCount
                    val firstVisibleItem =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                    if (loading) {
                        if (totalItemCount > previousTotal) {
                            loading = false;
                            previousTotal = totalItemCount;
                        }
                    }
                    if ((visibleItemCount + firstVisibleItem) >= totalItemCount) {
                        if (data==2) {
                            viewModel.getNewMovies("vote_count.desc", pageRated, data)
                            pageRated++
                        }else{
                            viewModel.getNewMovies("vote_count.desc", pagePlaying, data)
                            pagePlaying++
                        }
                        loading = true
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}
