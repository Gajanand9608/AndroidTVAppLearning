package com.example.tv3.activities.ui

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.tv.foundation.PivotOffsets
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.itemsIndexed
import androidx.tv.foundation.lazy.list.rememberTvLazyListState
import androidx.tv.material3.Border
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.example.tv3.R
import com.example.tv3.activities.ImageCarouselActivity
import com.example.tv3.activities.constants.ItemType
import com.example.tv3.activities.ui.theme.LightBlack1
import com.example.tv3.activities.ui.theme.LightBlack2
import com.example.tv3.activities.ui.theme.Typography
import com.example.tv3.model_new.VideoData
import com.example.tv3.player.PlaybackActivity
import com.example.tv3.viewModel.MainViewModel

@ExperimentalTvMaterial3Api
@Composable
fun ImmersiveListScreen(
    videos: List<VideoData>,
    images: List<String>,
    mainViewModel: MainViewModel
) {

    val context = LocalContext.current

    val backgroundImage = mainViewModel.focusImageState.value

    Box(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            model = backgroundImage ?: R.drawable.bg_banner,
            contentDescription = null,
            placeholder = painterResource(
                id = R.drawable.bg_banner
            ),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    val brush = Brush.horizontalGradient(
                        listOf(LightBlack2, LightBlack1)
                    )
                    drawRect(brush)
                }
        ) {

        }

        ScrollableItems(videos, images) {
            mainViewModel.setFocusImageState(it)
        }
    }
}


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ScrollableItems(
    list2: List<VideoData>,
    images: List<String> = emptyList(),
    onFocus: (String?) -> Unit
) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val offsetHeight = screenHeight / 2
    val cardSpacing = 20.dp
    val scrollState = rememberTvLazyListState()


    TvLazyColumn(
        state = scrollState,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .offset(y = offsetHeight)
    ) {
        item {
            Text(
                text = "Videos",
                style = Typography.headlineLarge,
                modifier = Modifier.padding(start = 60.dp)
            )
        }

        item {
            TvLazyRow(
                pivotOffsets = PivotOffsets(0.1f),
                horizontalArrangement = Arrangement.spacedBy(cardSpacing),
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = 60.dp,
                    end = 30.dp,
                    top = 40.dp,
                    bottom = 30.dp
                )
            ) {
                itemsIndexed(list2) { index, item ->
                    BannerItem(ItemType.VIDEO, item, null, enabledClick = false) {
                        onFocus(it)
                    }
                }
            }
        }

        item {
            Text(
                text = "Images",
                style = Typography.headlineLarge,
                modifier = Modifier.padding(start = 60.dp)
            )
        }

        item {
            TvLazyRow(
                pivotOffsets = PivotOffsets(0.1f),
                horizontalArrangement = Arrangement.spacedBy(cardSpacing),
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(
                    start = 60.dp,
                    end = 30.dp,
                    top = 40.dp,
                    bottom = 30.dp
                )
            ) {
                item {
                    val slideShowButtonUrl =
                        "https://firebasestorage.googleapis.com/v0/b/chatapp-d37e0.appspot.com/o/Firefly%20image%20slide%20slideshow%20play%20button%20with%20vibrat%20color%2046632.jpg?alt=media&token=efce5349-3af2-4330-b281-51dc8f6ce91b"
                    BannerItem(ItemType.IMAGE, null, slideShowButtonUrl , enabledClick = true) {
                        onFocus(it)
                    }
                }
                itemsIndexed(images) { index, item ->
                    BannerItem(ItemType.IMAGE, null, item, enabledClick = false) {
                        onFocus(it)
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun BannerItem(
    itemType: ItemType, item: VideoData?, imageUrl: String?,
    enabledClick: Boolean = false,
    onFocus: (String?) -> Unit
) {
    val cardWidth = 196.dp
    val cardHeight = 110.dp
    val context = LocalContext.current
    val slideShowButtonUrl =
        "https://firebasestorage.googleapis.com/v0/b/chatapp-d37e0.appspot.com/o/Firefly%20image%20slide%20slideshow%20play%20button%20with%20vibrat%20color%2046632.jpg?alt=media&token=efce5349-3af2-4330-b281-51dc8f6ce91b"

    if (itemType == ItemType.VIDEO) {
        Column(
            modifier = Modifier
                .width(cardWidth)
                .wrapContentHeight()
                .onFocusEvent {
                    if (it.isFocused) {
                        onFocus(item?.imageUrl)
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                onClick = {
                    val intent = Intent(context, PlaybackActivity::class.java)
                    intent.putExtra("videoUri", item?.videoUrl)
                    ContextCompat.startActivity(context, intent, null)
                },
                modifier = Modifier
                    .width(cardWidth)
                    .height(cardHeight),
                glow = CardDefaults.glow(
                    focusedGlow = Glow(
                        elevationColor = Color.White,
                        elevation = 6.dp
                    ),
                ),
                border = CardDefaults.border(
                    focusedBorder = Border(
                        BorderStroke(
                            2.dp,
                            Color.White
                        )
                    )
                ),
            ) {
                AsyncImage(
                    model = item?.imageUrl,
                    contentDescription = item?.title,
                    contentScale = ContentScale.FillBounds,
                    placeholder = painterResource(R.drawable.bg_banner),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item?.title.toString(), style = Typography.bodyLarge)
        }
    } else {
        Column(
            modifier = Modifier
                .width(cardWidth)
                .wrapContentHeight()
                .onFocusEvent {
                    if (it.isFocused) {
                        onFocus(imageUrl)
                    }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                onClick = {
                    if(enabledClick){
                        val intent = Intent(context, ImageCarouselActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)
                    }
                },
                modifier = Modifier
                    .width(cardWidth)
                    .height(cardHeight),
                glow = CardDefaults.glow(
                    focusedGlow = Glow(
                        elevationColor = Color.White,
                        elevation = 6.dp
                    ),
                ),
                border = CardDefaults.border(
                    focusedBorder = Border(
                        BorderStroke(
                            2.dp,
                            Color.White
                        )
                    )
                ),
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = item?.title,
                    contentScale = ContentScale.FillBounds,
                    placeholder = painterResource(R.drawable.bg_banner),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item?.title.toString(), style = Typography.bodyLarge)
        }
    }
}