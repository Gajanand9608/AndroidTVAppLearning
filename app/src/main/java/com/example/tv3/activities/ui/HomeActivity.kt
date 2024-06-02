package com.example.tv3.activities.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.example.tv3.activities.ui.theme.TV3Theme
import com.example.tv3.viewModel.MainViewModel

class HomeActivity : ComponentActivity() {

    val mainViewModel : MainViewModel by viewModels()
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val videos = mainViewModel.videoState.value
            val nonNullVideos = videos?.filterNotNull() ?: emptyList()
            TV3Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ImmersiveListScreen(nonNullVideos,mainViewModel)
                }
            }
        }
    }
}