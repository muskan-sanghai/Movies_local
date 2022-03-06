package com.example.movieslocal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MovieDetailActivity : AppCompatActivity() {
    private val TAG = "MovieDetailActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        //Log.d(TAG, "onCreate: ${intent.getIntExtra("id", 0)}")
        //loadMovieDetail(intent.getIntExtra("id", 0).toString(), this)
        loadMovieDetail(intent.getStringExtra("title").toString(),intent.getStringExtra("overview").toString(),intent.getStringExtra("poster_path").toString())
    }

    private fun loadMovieDetail(title: String, overview: String, poster_path: String) {
           updateUI(title, overview, poster_path)

    }

    private fun updateUI(title: String, overview: String, poster_path:String ) {

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + poster_path)
            .into(findViewById<ImageView>(R.id.movie_poster))
        findViewById<TextView>(R.id.movie_title).text = title
        findViewById<TextView>(R.id.description).text = overview
    }
}