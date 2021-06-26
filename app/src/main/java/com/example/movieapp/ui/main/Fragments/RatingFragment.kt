package com.example.movieapp.ui.main.Fragments

import android.os.Bundle
import android.util.Log
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
import androidx.recyclerview.widget.RecyclerView.OnScrollListener as OnScrollListener1

class RatingFragment : Fragment(), OnClickImp {
    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MainViewModel
    private var page = 2
    private var loading = true
    private var previousTotal = 0


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

        recyclerView = binding.recycleRating

        viewModel.liveDataRated.observe(viewLifecycleOwner, {
            showList(it.results)
        })
    }

    fun showList(list: List<Movie>) {
        binding.recycleRating.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount =
                        (binding.recycleRating.layoutManager as GridLayoutManager).childCount
                    val totalItemCount =
                        (binding.recycleRating.layoutManager as GridLayoutManager).itemCount
                    val firstVisibleItem =
                        (binding.recycleRating.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()

                    if (loading) {
                        if (totalItemCount > previousTotal) {
                            loading = false;
                            previousTotal = totalItemCount;
                        }
                    }
                    if ((visibleItemCount + firstVisibleItem) >= totalItemCount) {
                        viewModel.getNewMovies("vote_count.desc",page,2)
                        page++
                        loading = true
                    }
                    super.onScrolled(recyclerView, dx, dy)
                }
            }
        })

        binding.recycleRating.layoutManager = GridLayoutManager(
            view?.context, 3,
            GridLayoutManager.VERTICAL, false
        )

        binding.recycleRating.adapter =
            AdapterMain(context, list.sortedByDescending { it.vote_average }, this)

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