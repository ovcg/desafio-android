package com.example.desafiopitang.ui.movie

import com.example.desafiopitang.data.models.Movie
import com.example.desafiopitang.ui.base.BaseContract
import io.reactivex.disposables.Disposable

interface MovieContract {

    interface View : BaseContract.View{
        fun onSuccess(msg : String)
        fun onError(msg : String)
        fun showProgress(show : Boolean)
        fun moviesResponse(movies : List<Movie>)
        fun movieDetail(movie: Movie)
    }

    interface Presenter : BaseContract.Presenter<View>{
        fun loadMovies(page:String, size:String)
        fun movieDetail(id : String)
    }

    interface GetMovieInteractor{
        fun loadMovies() : Disposable
        fun movieDetail(id : String) : Disposable
    }
}