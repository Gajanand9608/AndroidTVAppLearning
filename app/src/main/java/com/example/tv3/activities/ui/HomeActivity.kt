package com.example.tv3.activities.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Card
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import coil.compose.AsyncImage
import com.example.tv3.R
import com.example.tv3.activities.ImageCarouselActivity
import com.example.tv3.activities.ui.theme.TV3Theme
import com.example.tv3.model2.CommonDataModel
import com.example.tv3.model3.CommonTVDataModel
import com.example.tv3.model3.Data
import com.example.tv3.player.PlaybackActivity
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class HomeActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TV3Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    App()
                }
            }
        }
    }
}


@Composable
fun App() {
    val context = LocalContext.current
    val gson = Gson()
    val i = context.assets?.open("temp.json")
    val br = BufferedReader(InputStreamReader(i))
    val dataList: CommonTVDataModel = gson.fromJson(br, CommonTVDataModel::class.java)
    val images = dataList.images.data
    val videos = dataList.videos.data

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Text(text = "Videos", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp))
        TvLazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.height(200.dp)
        ) {
            items(videos) { video ->
                MovieCard(
                    type = "videos",
                    video,
                    onClick = {
                        if (it.second == "videos") {
                            val intent = Intent(context, PlaybackActivity::class.java)
                            intent.putExtra("videoUri", it.first)
                            startActivity(context, intent, null)
                        } else {
                            val intent = Intent(context, ImageCarouselActivity::class.java)
                            startActivity(context, intent, null)
                        }
                    }
                )
            }
        }

        Text(text = "Images", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp))
        TvLazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.height(200.dp)
        ) {
            items(images) { model ->
                MovieCard(
                    type = "images",
                    model,
                    onClick = {
                        val intent = Intent(context, ImageCarouselActivity::class.java)
                        startActivity(context, intent, null)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MovieCard(
    type : String = "videos",
    dataModel: Data,
    modifier: Modifier = Modifier,
    onClick: (Pair<String, String>) -> Unit = {}
) {
    Card(
        onClick = {
            if (type == "videos") {
                onClick(Pair(dataModel.videoUri, "videos"))
            } else {
                onClick(Pair(dataModel.backgroundImage, "images"))
            }
        },
        modifier = modifier
            .widthIn(max = 320.dp)
            .aspectRatio(16f / 9f),
    ) {
        AsyncImage(
            model = dataModel.backgroundImage,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_movie)
        )
    }
}