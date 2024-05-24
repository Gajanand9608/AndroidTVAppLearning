package com.example.tv3.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.tv3.R
import com.example.tv3.activities.ImageCarouselActivity
import com.example.tv3.databinding.FragmentHomeBinding
import com.example.tv3.model2.ImageModel
import com.example.tv3.model2.TvDataModel
import com.example.tv3.model2.CommonDataModel
import com.example.tv3.player.PlaybackActivity
import com.example.tv3.viewModel.MainViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.io.BufferedReader
import java.io.InputStreamReader

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private val viewModel: MainViewModel by viewModels()
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
        val i =  context?.assets?.open("temp.json")
        val br = BufferedReader(InputStreamReader(i))
        val dataList : TvDataModel = gson.fromJson(br,TvDataModel::class.java)

//        listFragment.bindData(dataList)

        listFragment.setOnVideoContentSelectedListener {
            updateBanner(it)
        }

        listFragment.setOnImageContentSelectedListener {
            updateBanner2(it)
        }

        listFragment.setOnVideoContentClickedListener{
            val intent = Intent(context, PlaybackActivity::class.java)
            intent.putExtra("videoUri",it.videoUri)
            startActivity(intent)
        }

        listFragment.setOnImageContentClickedListener {
            val intent = Intent(context, ImageCarouselActivity::class.java)
            startActivity(intent)
        }
    }



    private fun updateBanner(movie: CommonDataModel) {
        val url = movie.backgroundImage
        Glide.with(this).load(url).into(binding.imgBanner)
    }

    private fun updateBanner2(image: ImageModel) {
        val url = image.imageUri
        Glide.with(this).load(url).into(binding.imgBanner)
    }
}