package com.example.tv3.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.tv.material3.Carousel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import coil.compose.AsyncImage
import com.example.tv3.R
import com.example.tv3.activities.ui.theme.TV3Theme
import com.example.tv3.model4.NewComposeModel
import com.example.tv3.viewModel.MainViewModel
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class ImageCarouselActivity : ComponentActivity() {
    val mainViewModel : MainViewModel by viewModels()

    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TV3Theme {
                val images = mainViewModel.imageUrlState.value
                val nonNullImages = images?.filterNotNull() ?: emptyList()
                Surface(modifier = Modifier.fillMaxSize()) {
                    ImageCarousel(nonNullImages)
                }
            }
        }
    }
}


@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ImageCarousel(images: List<String>) {
    val context = LocalContext.current

    Carousel(
        itemCount = images.size,
        autoScrollDurationMillis = 3000,
        modifier = Modifier
            .fillMaxSize(),
        carouselIndicator = {}
    ) { indexOfCarouselItem ->
        val currentImageModel = images[indexOfCarouselItem]
        AsyncImage(
            model = currentImageModel,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .animateEnterExit(
                    enter = scaleIn(
                        animationSpec = tween(
                            durationMillis = 1000,
                            easing = FastOutSlowInEasing
                        )
                    ),
                )
        )
    }
}