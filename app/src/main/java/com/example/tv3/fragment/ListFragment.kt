package com.example.tv3.fragment

import android.media.Image
import android.os.Bundle
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.OnItemViewSelectedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import com.example.tv3.model.Detail
import com.example.tv3.model2.ImageModel
import com.example.tv3.model2.TvDataModel
import com.example.tv3.model2.VideoModel
import com.example.tv3.model2.Videos
import com.example.tv3.presenter.ItemPresenter

class ListFragment : RowsSupportFragment() {

    private var itemVideoSelectedListener: ((VideoModel) -> Unit)? = null
    private var itemVideoClickedListener : ((VideoModel) -> Unit)? = null

    private var itemImageSelectedListener: ((ImageModel) -> Unit)? = null
    private var itemImageClickedListener : ((ImageModel) -> Unit)? = null



    private val listRowPresenter = object : ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM){
        override fun isUsingDefaultListSelectEffect(): Boolean {
            return false
        }
    }.apply {
        shadowEnabled = false
    }

    private val rootAdapter : ArrayObjectAdapter = ArrayObjectAdapter(listRowPresenter)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = rootAdapter
        onItemViewSelectedListener = ItemViewSelectedListener()
        onItemViewClickedListener = ItemViewClickedListener()
    }

    fun bindData(data: TvDataModel) {
        val arrayObjectAdapter = ArrayObjectAdapter(ItemPresenter())
        data.videos.data.forEachIndexed { index, result ->
            arrayObjectAdapter.add(result)
        }
        val headerItem = HeaderItem(data.videos.title)
        val listRow = ListRow(headerItem, arrayObjectAdapter)
        rootAdapter.add(listRow)

        val arrayObjectAdapter2 = ArrayObjectAdapter(ItemPresenter())

        val temp = ImageModel(imageUri = "https://firebasestorage.googleapis.com/v0/b/chatapp-d37e0.appspot.com/o/image.png?alt=media&token=4e4dc296-fd0e-4023-b16f-c62a9ff2e4fd", title = "Play Image SlideShow")

        arrayObjectAdapter2.add(temp)
        data.images.data.forEachIndexed { index, result ->
            arrayObjectAdapter2.add(result)
        }
        val headerItem2 = HeaderItem(data.images.title)
        val listRow2 = ListRow(headerItem2, arrayObjectAdapter2)

        rootAdapter.add(listRow2)
    }

    fun setOnVideoContentSelectedListener(listener : (VideoModel) -> Unit){
        this.itemVideoSelectedListener = listener
    }

    fun setOnVideoContentClickedListener(listener : (VideoModel) -> Unit){
        this.itemVideoClickedListener = listener
    }

    fun setOnImageContentSelectedListener(listener : (ImageModel) -> Unit){
        this.itemImageSelectedListener = listener
    }

    fun setOnImageContentClickedListener(listener : (ImageModel) -> Unit){
        this.itemImageClickedListener = listener
    }


    inner class ItemViewSelectedListener : OnItemViewSelectedListener {
        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if(item is VideoModel){
                itemVideoSelectedListener?.invoke(item)
            }else if(item is ImageModel){
                itemImageSelectedListener?.invoke(item)
            }
        }
    }

    inner class ItemViewClickedListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
           if(item is VideoModel){
               itemVideoClickedListener?.invoke(item)
           }else if(item is ImageModel){
               itemImageClickedListener?.invoke(item)
           }
        }
    }
}