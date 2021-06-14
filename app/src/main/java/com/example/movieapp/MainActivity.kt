package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movieapp.databinding.MainActivityBinding
import com.example.movieapp.ui.main.Fragments.FavoritesFragment
import com.example.movieapp.ui.main.Fragments.RatingFragment
import com.example.movieapp.ui.main.MainFragment
import com.example.movieapp.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding : MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(MainFragment())

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.favorites -> setFragment(FavoritesFragment())
                R.id.home -> setFragment(MainFragment())
                R.id.rating -> setFragment(RatingFragment())
            }
            true
        }
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container, fragment)
            commit()
        }
    }
}