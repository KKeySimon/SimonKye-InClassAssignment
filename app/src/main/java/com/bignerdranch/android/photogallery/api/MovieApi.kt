package com.bignerdranch.android.photogallery.api

import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "17af2d83"


interface MovieApi {
    @GET(
        "?apikey=$API_KEY"
    )
    suspend fun fetchMovies(@Query("t") movieTitle: String): MovieItem
}
