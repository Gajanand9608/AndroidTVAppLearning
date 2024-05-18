package com.example.tv3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tv3.databinding.ActivityMainBinding
import com.example.tv3.databinding.FragmentHomeBinding
import com.example.tv3.model.Detail
import com.example.tv3.model.MoviesDataModel
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val listFragment = ListFragment()
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.list_fragment, listFragment)
        transaction.commit()

        val gson = Gson()
        val i =  context?.assets?.open("movies.json")
        val br = BufferedReader(InputStreamReader(i))
        val dataList : MoviesDataModel = gson.fromJson(br,MoviesDataModel::class.java)

        listFragment.bindData(dataList)

        listFragment.setOnContentSelectedListener {
            updateBanner(it)
        }
    }


    private fun updateBanner(movie: Detail) {
        binding.title.text = movie.title
        binding.description.text = movie.overview
        Glide.with(this).load(movie.poster_path).into(binding.imgBanner)
    }
}