package com.example.tv3.player

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.KeyEvent
import android.view.View
import androidx.leanback.media.PlaybackTransportControlGlue
import androidx.leanback.widget.Action
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.PlaybackControlsRow
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.tv3.model.DetailResponseModel

class CustomTransportControlGlue(
    context: Context,
    playerAdapter: BasicMediaPlayerAdapter
) : PlaybackTransportControlGlue<BasicMediaPlayerAdapter>(context, playerAdapter) {

    // primary actions
    private val forwardAction = PlaybackControlsRow.FastForwardAction(context)
    private val rewindAction = PlaybackControlsRow.RewindAction(context)
    private val nextAction = PlaybackControlsRow.SkipNextAction(context)
    private val previousAction = PlaybackControlsRow.SkipPreviousAction(context)

    init {
        isSeekEnabled= true
    }

    override fun onCreatePrimaryActions(primaryActionsAdapter: ArrayObjectAdapter?) {
        primaryActionsAdapter?.add(previousAction)
        primaryActionsAdapter?.add(rewindAction)
        super.onCreatePrimaryActions(primaryActionsAdapter)
        primaryActionsAdapter?.add(forwardAction)
        primaryActionsAdapter?.add(nextAction)
    }

    override fun onActionClicked(action: Action?) {
        when(action){
            forwardAction -> playerAdapter.fastForward()
            rewindAction -> playerAdapter.rewind()
            else ->  super.onActionClicked(action)
        }
        onUpdateProgress()
    }

    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if(host.isControlsOverlayVisible || event?.repeatCount!! > 0) return super.onKey(v, keyCode, event)
        return when(keyCode){
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                if(event.action != KeyEvent.ACTION_DOWN) false else {
                    onActionClicked(forwardAction)
                    true
                }
            }

            KeyEvent.KEYCODE_DPAD_LEFT -> {
                if(event.action != KeyEvent.ACTION_DOWN) false else {
                    onActionClicked(rewindAction)
                    true
                }
            }

            else -> {
                super.onKey(v, keyCode, event)
            }
        }
    }

    fun loadMovieInfo(detailResponseModel: DetailResponseModel?){
        title = detailResponseModel?.title ?: "No Title"
        subtitle = detailResponseModel?.let { getSubtitle(detailResponseModel) } ?: "No Oberview"

        val uriPath = "https://firebasestorage.googleapis.com/v0/b/chatapp-d37e0.appspot.com/o/Y2meta.app%20-%20copyright%20free%20nature%20videos%20_%20No%20copyright%20video%20nature%20_%20Download%20copyright%20free%20nature%20video.mp4?alt=media&token=44585b74-89fe-4341-b5a5-018030d095df"
        playerAdapter.setDataSource(Uri.parse(uriPath))


        val path = "https://www.themoviedb.org/t/p/w780" + (detailResponseModel?.backdrop_path ?: "")
        Glide.with(context)
            .asBitmap()
            .load(path)
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    controlsRow.setImageBitmap(context,resource)
                    host.notifyPlaybackRowChanged()
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    controlsRow.setImageBitmap(context,null)
                    host.notifyPlaybackRowChanged()
                }
            })

        playWhenPrepared()
    }

    private fun getSubtitle(detailResponseModel: DetailResponseModel): String {
        val rating = if(detailResponseModel.adult){
            "18+"
        }else{
            "13+"
        }

        val genres = detailResponseModel.genres.joinToString(prefix = " ", postfix = " . ", separator = " . ") {it.name}
        val hours : Int = detailResponseModel.runtime / 60
        val min : Int = detailResponseModel.runtime % 60
        return rating + genres + hours + "h " + min + " m"
    }
}