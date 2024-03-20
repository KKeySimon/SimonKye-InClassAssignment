package com.bignerdranch.android.photogallery.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MovieItem (
    val Title: String,
    val Year: String,
    val Poster: String
)