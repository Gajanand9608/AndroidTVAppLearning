package com.example.tv3.activities

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.tv3.MyApplication
import com.example.tv3.R
import com.example.tv3.api.ResponseState
import com.example.tv3.databinding.ActivityDetailBinding
import com.example.tv3.fragment.ListFragment
import com.example.tv3.model.DetailResponseModel
import com.example.tv3.model.MovieCastDetailModel
import com.example.tv3.player.PlaybackActivity
import com.example.tv3.utils.Common
import com.example.tv3.utils.Common.Companion.isEllipsized
import com.example.tv3.viewModel.DetailViewModel
import com.example.tv3.viewModel.DetailViewModelFactory

class DetailActivity : FragmentActivity() {

    private lateinit var binding : ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel
    private val castFragment  = ListFragment()

    private var movieId : Int = 0
    var detailResponse : DetailResponseModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addCastFragment()

        if(intent != null && intent.hasExtra("id")){
            movieId = intent.getIntExtra("id",0)
        }

        val repository = (application as MyApplication).tmdbRepo
        viewModel = ViewModelProvider(this, DetailViewModelFactory(repository,movieId)).get(DetailViewModel::class.java)

        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.addToMylist.setOnKeyListener { view, keyCode, keyEvent ->
            when(keyCode){
                KeyEvent.KEYCODE_DPAD_DOWN -> {
                    if(keyEvent.action == KeyEvent.ACTION_DOWN){
                        castFragment.requestFocus()
                    }
                }
            }
            false
        }

        binding.play.setOnClickListener {
            val intent = Intent(this, PlaybackActivity::class.java)
            intent.putExtra("movie_detail",detailResponse)
            startActivity(intent)
        }
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
                        detailResponse = it
                        setData(it)
                    }
                }
            }
        }

        viewModel.castDetailLiveData.observe(this){
            when(it){
                is ResponseState.Error -> {

                }
                is ResponseState.Loading -> {

                }
                is ResponseState.Success -> {
                    if(it.data?.cast.isNullOrEmpty().not()){
                        castFragment.bindCastData(it.data?.cast!!)
                    }
                }
            }
        }
    }

    private fun setData(model: DetailResponseModel) {
        binding.title.text = model.title
        binding.subtitle.text = getSubtitle(model)
        binding.description.text = model.overview

        val path = "https://www.themoviedb.org/t/p/w780" + (model.backdrop_path ?: "")
        Glide.with(this)
            .load(path)
            .into(binding.imgBanner)

        binding.description.isEllipsized { isEllipsized ->
            binding.showMore.visibility = if (isEllipsized) View.VISIBLE else View.GONE

            binding.showMore.setOnClickListener {
                Common.descriptionDialog(
                    this,
                    model.title,
                    getSubtitle(model),
                    model.overview.toString()
                )
            }
        }

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

    private fun addCastFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.cast_fragment, castFragment)
        transaction.commit()
    }
}