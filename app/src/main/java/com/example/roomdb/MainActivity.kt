package com.example.roomdb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdb.room.MovieDb
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val db by lazy { MovieDb( this) }
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListener()
        setupRecyclerView()

    }

    override fun onStart(){
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
           val movies = db.movieDao().getMovies()
            Log.d("MainActivity", "dbresponse: $movies")
            withContext(Dispatchers.Main){
                movieAdapter.setData(movies)
            }

        }
    }

    fun setupListener() {
        add_movie.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }

    private fun setupRecyclerView(){
        movieAdapter = MovieAdapter(arrayListOf())
        rv_movie.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = movieAdapter

        }
    }

}