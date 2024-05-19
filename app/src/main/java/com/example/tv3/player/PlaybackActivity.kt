package com.example.tv3.player

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.tv3.R

class PlaybackActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_playback)
        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.content, MyVideoFragment()).commit()
        }
    }
}