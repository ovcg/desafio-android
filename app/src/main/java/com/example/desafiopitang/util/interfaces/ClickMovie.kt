package com.example.desafiopitang.util.interfaces

import com.example.desafiopitang.data.models.Movie

interface ClickMovie {
    fun movieSelected(movie: Movie)
}