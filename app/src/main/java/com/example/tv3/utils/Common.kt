package com.example.tv3.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.TextView
import com.example.tv3.R

class Common {

    companion object {
        fun getWidthInPercent(context: Context, percent: Int): Int {
            val width = context.resources.displayMetrics.widthPixels ?: 0
            return (width * percent) / 100
        }

        fun getHeightInPercent(context: Context, percent: Int): Int {
            val height = context.resources.displayMetrics.heightPixels ?: 0
            return (height * percent) / 100
        }
    }
}