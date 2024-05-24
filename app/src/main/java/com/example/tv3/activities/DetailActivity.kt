package com.example.tv3.activities

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.example.tv3.databinding.ActivityDetailBinding
import com.example.tv3.model.DetailResponseModel
import com.example.tv3.model2.CommonDataModel
import com.example.tv3.player.PlaybackActivity

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

    private fun setData(model: CommonDataModel) {
        binding.title.text = model.title

        val path = model.backgroundImage
        Glide.with(this)
            .load(path)
            .into(binding.imgBanner)
    }
}