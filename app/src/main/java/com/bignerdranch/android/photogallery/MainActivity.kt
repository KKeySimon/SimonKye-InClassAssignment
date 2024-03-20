package com.bignerdranch.android.photogallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import coil.load
import com.bignerdranch.android.photogallery.api.MovieApi
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    val TAG = "TAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val movieApi: MovieApi = retrofit.create<MovieApi>()


        val editText: EditText = findViewById(R.id.editText)
        val button: Button = findViewById(R.id.button)
        val title: TextView = findViewById(R.id.title)
        val year: TextView = findViewById(R.id.year)
        val imageView: ImageView = findViewById(R.id.item_image_view)

        button.setOnClickListener {
            val movieTitle = editText.text.toString()
            lifecycleScope.launch {
                try {
                    val items = movieApi.fetchMovies(movieTitle)
                    Log.d(TAG, "Items received: ${items.Title}")
                    title.text = items.Title
                    year.text = items.Year
                    imageView.load(items.Poster)
                } catch (ex: Exception) {
                    Log.e(TAG, "Failed to fetch movie items", ex)
                }
            }
        }


    }
}