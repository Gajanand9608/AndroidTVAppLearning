package com.example.tv3.activities

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.tv3.MyApplication
import com.example.tv3.api.ResponseState
import com.example.tv3.databinding.ActivityDetailBinding
import com.example.tv3.model.DetailResponseModel
import com.example.tv3.viewModel.DetailViewModel
import com.example.tv3.viewModel.DetailViewModelFactory
import com.google.android.material.progressindicator.CircularProgressIndicator

class DetailActivity : FragmentActivity() {

    private lateinit var binding : ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var movieId : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent != null && intent.hasExtra("id")){
            movieId = intent.getIntExtra("id",0)
        }

        val repository = (application as MyApplication).tmdbRepo
        viewModel = ViewModelProvider(this, DetailViewModelFactory(repository,movieId)).get(DetailViewModel::class.java)


        initObservers()

    }

    private fun initObservers(){
        viewModel.movieDetailLiveData.observe(this){
            when(it){
                is ResponseState.Error -> {
                    Toast.makeText(this, it.error,Toast.LENGTH_SHORT ).show()
                }
                is ResponseState.Loading -> {
                    Toast.makeText(this, "Loading",Toast.LENGTH_SHORT ).show()
                }
                is ResponseState.Success -> {
                    it.data?.let {
                        setData(it)
                    }
                }
            }
        }
    }

    private fun setData(it: DetailResponseModel) {
        binding.title.text = it.title
        binding.subtitle.text = getSubtitle(it)
        binding.description.text = it.overview

        val path = "https://www.themoviedb.org/t/p/w780" + (it.backdrop_path ?: "")
        Glide.with(this)
            .load(path)
            .into(binding.imgBanner)

    }

    private fun getSubtitle(detailResponseModel: DetailResponseModel): String {
        val rating = if(detailResponseModel.adult){
            "18+"
        }else{
            "13+"
        }

        val genres = detailResponseModel.genres.joinToString(prefix = " ", postfix = " . ", separator = " . ") {it.name}
        val hours : Int = detailResponseModel.runtime / 60
        val min : Int = detailResponseModel.runtime % 60
        return rating + genres + hours + "h " + min + " m"
    }
}