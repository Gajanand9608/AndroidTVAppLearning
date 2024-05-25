package com.example.tv3.player

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.tv3.R

class PlaybackActivity : FragmentActivity() {
    private var videoUri : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_playback)

        if(intent != null && intent.hasExtra("videoUri")){
            videoUri = intent.getStringExtra("videoUri")
        }


        val fragment = MyVideoFragment()
        val bundle = Bundle()
        bundle.putString("videoUri", videoUri)
        fragment.arguments = bundle
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
        }
    }
}