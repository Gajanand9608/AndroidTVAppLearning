package com.example.tv3.player

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.tv3.R
import com.example.tv3.model.DetailResponseModel

class PlaybackActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_playback)

        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("movie_detail",DetailResponseModel::class.java)
        } else {
            intent.getParcelableExtra<DetailResponseModel>("movie_detail")
        }

        val fragment = MyVideoFragment()
        val bundle = Bundle()
        bundle.putParcelable("movie_detail", data)
        fragment.arguments = bundle

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
        }
    }
}