package com.example.movieapi.helpers

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.movieapi.models.MovieListResponse
import com.example.movieslocal.R

class MovieAdapter(private val moviesList: List<MovieListResponse>, private val onClick: (MovieListResponse)-> Unit) :RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return ViewHolder(view, onClick)
    }

    override fun getItemCount(): Int {

        return moviesList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${moviesList.size} ")

        return holder.bind(moviesList[position])

    }
    class ViewHolder(itemView : View, private val onClick: (MovieListResponse)-> Unit) :RecyclerView.ViewHolder(itemView) {


        var imageView = itemView.findViewById<ImageView>(R.id.movie_poster)
        var movieTitle = itemView.findViewById<TextView>(R.id.movie_title)
        var releaseDate = itemView.findViewById<TextView>(R.id.release_date)
        fun bind(movie: MovieListResponse) {
            itemView.setOnClickListener{

                onClick(movie)


            }
            val name ="Release Date: ${movie.release_date.toString()}"
            movieTitle.text = movie.title
            releaseDate.text = name
            val imageURL = "https://image.tmdb.org/t/p/w500" + movie.poster_path
            //println("hello  " + imageURL)
            Picasso.get().load(imageURL).into(imageView)
        }

    }
}