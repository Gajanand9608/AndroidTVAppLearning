package com.example.tv3.fragment

import android.net.Uri
import android.os.Bundle
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.MediaPlayerAdapter
import androidx.leanback.media.PlaybackGlue
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.PlaybackSeekDataProvider

class MyVideoFragment : VideoSupportFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val playerGlue = PlaybackTransportControlGlue(activity,MediaPlayerAdapter(activity))
        playerGlue.host = VideoSupportFragmentGlueHost(this)
        playerGlue.addPlayerCallback(object : PlaybackGlue.PlayerCallback(){
            override fun onPreparedStateChanged(glue: PlaybackGlue?) {
                if(glue?.isPrepared == true){
                    playerGlue.seekProvider = PlaybackSeekDataProvider()
                    playerGlue.play()
                }
            }
        })

        playerGlue.title = "Android TV"
        playerGlue.subtitle = "Demo subtitle"

        val uriPath = "https://firebasestorage.googleapis.com/v0/b/chatapp-d37e0.appspot.com/o/Y2meta.app%20-%20copyright%20free%20nature%20videos%20_%20No%20copyright%20video%20nature%20_%20Download%20copyright%20free%20nature%20video.mp4?alt=media&token=44585b74-89fe-4341-b5a5-018030d095df"
        playerGlue.playerAdapter.setDataSource(Uri.parse(uriPath))

    }
}