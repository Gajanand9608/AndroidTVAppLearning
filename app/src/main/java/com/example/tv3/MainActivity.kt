package com.example.tv3

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.example.tv3.databinding.ActivityMainBinding
import com.example.tv3.model.Detail
import com.example.tv3.model.MoviesDataModel
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : FragmentActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listFragment = ListFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.list_fragment, listFragment)
        transaction.commit()

        val gson = Gson()
        val i =  this.assets.open("movies.json")
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