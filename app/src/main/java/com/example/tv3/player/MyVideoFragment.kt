package com.example.tv3.player

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.view.isVisible
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.MediaPlayerAdapter
import androidx.leanback.media.PlaybackGlue
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.PlaybackSeekDataProvider
import com.example.tv3.R
import com.example.tv3.model.DetailResponseModel

class MyVideoFragment : VideoSupportFragment() {

    private lateinit var transportGlue: CustomTransportControlGlue
    private lateinit var fastForwardIndicatorView: View
    private lateinit var rewindIndicatorView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("movie_detail", DetailResponseModel::class.java)
        } else {
            arguments?.getParcelable<DetailResponseModel>("movie_detail")
        }

        transportGlue =
            CustomTransportControlGlue(requireContext(), BasicMediaPlayerAdapter(requireContext()))
        transportGlue.host = VideoSupportFragmentGlueHost(this)
        transportGlue.loadMovieInfo(data)


//        setOnKeyInterceptListener { v, keyCode, event ->
//            if (isControlsOverlayVisible || event.repeatCount > 0) {
////                showControlsOverlay(true)
//            } else {
//                when (keyCode) {
//                    KeyEvent.KEYCODE_DPAD_RIGHT -> {
////                        showControlsOverlay()
//                        if (event.action == KeyEvent.ACTION_DOWN) {
//                            animateIndicator(fastForwardIndicatorView)
//                        }
//                    }
//
//                    KeyEvent.KEYCODE_DPAD_LEFT -> {
////                        showControlsOverlay()
//                        if (event.action == KeyEvent.ACTION_DOWN) {
//                            animateIndicator(rewindIndicatorView)
//                        }
//                    }
//                }
//            }
//
//            transportGlue.onKey(v, keyCode, event)
//        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  super.onCreateView(inflater, container, savedInstanceState) as ViewGroup
        fastForwardIndicatorView = inflater.inflate(R.layout.view_forward,view,false)
        view.addView(fastForwardIndicatorView)

        rewindIndicatorView = inflater.inflate(R.layout.view_rewind,view,false)
        view.addView(rewindIndicatorView)

        return view
    }
    fun animateIndicator(indicator: View) {
        indicator.animate().withEndAction {
            indicator.isVisible = true
            indicator.alpha = 1f
            indicator.scaleX = 1f
            indicator.scaleY = 1f
        }
            .withStartAction {
                indicator.isVisible = true

            }.alpha(0.2f)
            .scaleX(2f)
            .scaleY(2f)
            .setDuration(400)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()
    }
}