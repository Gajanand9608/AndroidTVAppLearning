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
import com.example.tv3.model2.VideoModel
import com.example.tv3.player.PlaybackActivity
import com.example.tv3.utils.Common
import com.example.tv3.utils.Common.Companion.isEllipsized
import com.example.tv3.viewModel.DetailViewModel
import com.example.tv3.viewModel.DetailViewModelFactory

class DetailActivity : FragmentActivity() {

    private lateinit var binding : ActivityDetailBinding

    private var videoUri : String? = null
    var detailResponse : DetailResponseModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(intent != null && intent.hasExtra("videoUri")){
            videoUri = intent.getStringExtra("videoUri")
        }

        initListeners()
    }

    private fun initListeners() {
        binding.play.setOnClickListener {
            videoUri?.let {
                val intent = Intent(this, PlaybackActivity::class.java)
                intent.putExtra("videoUri",it)
                startActivity(intent)
            }
        }
    }

    private fun setData(model: VideoModel) {
        binding.title.text = model.title

        val path = model.backgroundImage
        Glide.with(this)
            .load(path)
            .into(binding.imgBanner)
    }
}