package com.example.tv3.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import com.example.tv3.R

class MyVideoFragment : VideoSupportFragment() {

    private lateinit var transportGlue: CustomTransportControlGlue
    private lateinit var fastForwardIndicatorView: View
    private lateinit var rewindIndicatorView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data =  arguments?.getString("videoUri")

        transportGlue =
            CustomTransportControlGlue(requireContext(), BasicMediaPlayerAdapter(requireContext()))
        transportGlue.host = VideoSupportFragmentGlueHost(this)
        transportGlue.loadMovieInfo(data)

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
}