package com.example.movieapp.ui.main.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.Adapters.AdapterMain
import com.example.movieapp.Interfaces.OnClickImp
import com.example.movieapp.R
import com.example.movieapp.Utils.Util
import com.example.movieapp.databinding.FragmentFavoritesBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.model.MovieData
import com.example.movieapp.room.FavoriteViewModel
import com.example.movieapp.ui.main.MainViewModel

class FavoritesFragment : Fragment(), OnClickImp {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    private lateinit var favViewModel: FavoriteViewModel


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
        favViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        favViewModel.getMovies().observe(viewLifecycleOwner,{
            if (it.size > 0){
                binding.textView3.visibility = View.GONE
            }
            showList(it)
        })

        (activity as AppCompatActivity).supportActionBar?.title = "Favorite Movies"

    }
    private fun showList(list: List<Movie>){
        binding.recycleFavorites.layoutManager = GridLayoutManager(
            view?.context,3,
            GridLayoutManager.VERTICAL, false
        )
        binding.recycleFavorites.adapter = AdapterMain(context, list, this)

    }

    
    override fun onCardClick(position: Int, list: List<Movie>) {
        val bundle = Bundle()
        bundle.putSerializable(Util.KEY, list.get(position))
        val fragment = DescriptionFragment()
        fragment.arguments = bundle
        setFragment(fragment)
    }

    override fun onFavClick(position: Int, list: List<Movie>) {}

    private fun setFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            addToBackStack(null)
            commit()
        }
    }


}