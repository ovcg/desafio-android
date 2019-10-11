package com.example.desafiopitang.util.interfaces

import com.example.desafiopitang.data.models.Movie

interface ClickMovieListener {
    fun movieSelected(movie: Movie)
}