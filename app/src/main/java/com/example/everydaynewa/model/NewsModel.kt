package com.example.everydaynewa.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
):Parcelable