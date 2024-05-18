package com.example.tv3.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
) : Parcelable