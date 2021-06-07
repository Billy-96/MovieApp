package com.example.movieapp.ui.main.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.R
import com.example.movieapp.Utils.Util
import com.example.movieapp.databinding.FragmentDescriptionBinding
import com.example.movieapp.databinding.MainFragmentBinding
import com.example.movieapp.model.Movie
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
        val note = bundle?.getSerializable(Util.key) as Movie

        binding.posterDesc.setImageResource(note.poster)
        binding.titleDesc.text = note.title
        binding.descDesc.text = note.desc
        val year = note.comingDate.year.toString()
        val month = note.comingDate.month.toString()
        val day = note.comingDate.day.toString()
        binding.dateDesc.text = "$day.$month.$year"
        binding.rateDesc.text = note.rating.toString()
    }
}