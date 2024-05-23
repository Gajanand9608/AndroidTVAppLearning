package com.example.tv3.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.Carousel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import coil.compose.AsyncImage
import com.example.tv3.R
import com.example.tv3.activities.ui.theme.TV3Theme
import com.example.tv3.model2.TvDataModel
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class ImageCarouselActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TV3Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ImageCarousel()
                }
            }
        }
    }
}


@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ImageCarousel(){
    val context = LocalContext.current
    val gson = Gson()
    val i =  context.assets?.open("temp.json")
    val br = BufferedReader(InputStreamReader(i))
    val dataList : TvDataModel = gson.fromJson(br, TvDataModel::class.java)
    val images = dataList.images.data
        Carousel(
            itemCount = images.size,
            autoScrollDurationMillis = 3000,
            modifier = Modifier
                .fillMaxSize(),
            carouselIndicator = {

            }
        ) { indexOfCarouselItem ->
            val currentImageModel = images[indexOfCarouselItem]
            AsyncImage(
                model = currentImageModel.imageUri,
                contentDescription = null,
                placeholder = painterResource(
                    id = R.drawable.bg_banner
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize().animateEnterExit(
                    enter = scaleIn(animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)),
                )
            )
        }
}