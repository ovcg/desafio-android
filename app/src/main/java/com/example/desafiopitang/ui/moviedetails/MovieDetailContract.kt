package com.example.desafiopitang.ui.moviedetails

import com.example.desafiopitang.data.models.MovieDetails
import com.example.desafiopitang.ui.base.BaseContract

interface MovieDetailContract {

    interface View : BaseContract.View{
        fun showProgress(show : Boolean)
        fun onError(msg : String)
        fun getMovie(movieDetails : MovieDetails)
    }

    interface Presenter : BaseContract.Presenter<View>{
        fun getMovie(id : String)
    }
}