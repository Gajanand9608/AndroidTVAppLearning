package com.example.tv3

import android.app.Application
import com.example.tv3.api.ApiService
import com.example.tv3.api.RetrofitHelper
import com.example.tv3.api.TmdbRepo
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {

    lateinit var tmdbRepo : TmdbRepo
    override fun onCreate() {
        super.onCreate()

        init()
    }

    private fun init(){
        val service = RetrofitHelper.getInstance().create(ApiService::class.java)
        tmdbRepo = TmdbRepo(service)
    }
}