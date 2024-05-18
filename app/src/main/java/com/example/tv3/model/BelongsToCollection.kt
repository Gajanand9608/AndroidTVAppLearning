package com.example.tv3.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class BelongsToCollection(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val poster_path: String
) : Parcelable