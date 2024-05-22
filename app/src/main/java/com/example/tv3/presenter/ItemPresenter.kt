package com.example.tv3.presenter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.bumptech.glide.Glide
import com.example.tv3.R
import com.example.tv3.model.Detail
import com.example.tv3.model.MoviesDataModel
import com.example.tv3.model.Result
import com.example.tv3.model2.VideoModel

class ItemPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup?): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_view, parent, false)
        val params = view.layoutParams
        parent?.let {
            params.height = getHeightInPercent(it.context, 30)
            params.width = getWidthInPercent(it.context, 20)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder?, item: Any?) {
        val content = item as? VideoModel

        val imageview = viewHolder?.view?.findViewById<ImageView>(R.id.poster_image)

        val url = content?.backgroundImage

        Glide.with(viewHolder?.view?.context!!)
            .load(url)
            .into(imageview!!)
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder?) {
    }

    private fun getWidthInPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.widthPixels ?: 0
        return (width * percent) / 100
    }

    private fun getHeightInPercent(context: Context, percent: Int): Int {
        val width = context.resources.displayMetrics.heightPixels ?: 0
        return (width * percent) / 100
    }
}