package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomdb.room.Movie
import com.example.roomdb.room.MovieDb
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    val db by lazy { MovieDb( this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        setupListener()
    }

    fun setupListener(){
        btn_save.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.movieDao().addMovie(
                    Movie(0, et_title.text.toString(),
                    et_description.text.toString())
                )

                finish()
            }
        }
    }
}