package com.example.desafiopitang.ui.movie

import com.example.desafiopitang.data.models.Movie
import com.example.desafiopitang.ui.base.BaseContract

interface MovieContract {

    interface View : BaseContract.View{
        fun onSuccess(msg : String)
        fun onError(msg : String)
        fun showProgress(show : Boolean)
        fun moviesResponse(movies : List<Movie>)
    }

    interface Presenter : BaseContract.Presenter<View>{
        fun loadMovies(page:String, size:String)
    }

}