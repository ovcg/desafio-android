package com.example.desafiopitang.ui.moviehistory

import android.content.Context
import com.example.desafiopitang.data.models.Movie
import com.example.desafiopitang.ui.base.BaseContract

interface MovieHistoryContract {

    interface View : BaseContract.View{
        fun showProgress(show : Boolean)
        fun onError(msg : String)
        fun getMovies(movies : List<Movie>)
    }

    interface Presenter : BaseContract.Presenter<View>{
        fun loadMovies(ctx : Context, key : String)
    }

}