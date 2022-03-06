package com.example.movieslocal

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.helpers.MovieAdapter
import com.example.movieapi.models.MovieListResponse
import com.example.movieslocal.models.MoviesRecieved
import com.google.gson.Gson
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonString = readJson(this)
        //tvJsonString.text = jsonString  they have used textview in xml file and its id is tvJsonString
        Log.d("MainActivity", jsonString)
        val moviesList = Gson().fromJson(jsonString, MoviesRecieved::class.java)
        Log.d("MainActivity", moviesList.toString())
    }

    private fun readJson(context:Context): String {

        var input: InputStream?=null
        var jsonString: String

        try {
            input = context.assets.open("First.json")
            val size = input.available()
            val buffer = ByteArray(size)

            input.read(buffer) //reading data from input to buffer

            jsonString = String(buffer)
            val moviesList = Gson().fromJson(jsonString, MoviesRecieved::class.java)

            var movieRecycler = findViewById(R.id.movie_recycler) as RecyclerView
            movieRecycler.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = MovieAdapter(moviesList.movielist, onClick = {
                    Log.d("onClick", "onResponse: $it")
                    val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                    intent.putExtra("title", it.title)
                    intent.putExtra("poster_path", it.poster_path)
                    intent.putExtra("overview", it.overview)


                    startActivity(intent)
                })
            }
            return jsonString
        }
        catch(ex: Exception){
            ex.printStackTrace()
            return "error"
        }
        finally{
            input?.close()
        }

    }
}