package com.example.desafiopitang.ui.moviedetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafiopitang.R
import com.example.desafiopitang.util.Constants

class MovieDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val id = intent.getStringExtra(Constants.movieId)?:""

    }
}
