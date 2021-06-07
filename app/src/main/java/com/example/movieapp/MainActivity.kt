package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.databinding.MainActivityBinding
import com.example.movieapp.ui.main.Fragments.FavoritesFragment
import com.example.movieapp.ui.main.Fragments.RatingFragment
import com.example.movieapp.ui.main.MainFragment
import com.example.movieapp.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding : MainActivityBinding
    val viewModel = MainViewModel()
    val favoritesFragment = FavoritesFragment()
    val ratingFragment = RatingFragment()
    val mainFragment = MainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.setFragment(supportFragmentManager,mainFragment)

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.favorites -> viewModel.setFragment(supportFragmentManager,favoritesFragment)
                R.id.home -> viewModel.setFragment(supportFragmentManager,mainFragment)
                R.id.rating -> viewModel.setFragment(supportFragmentManager,ratingFragment)
            }
            true
        }
    }
}