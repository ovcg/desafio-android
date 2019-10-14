package com.example.desafiopitang.ui.moviehistory

import android.content.Context
import com.example.desafiopitang.data.models.Movie
import com.example.desafiopitang.util.Constants
import com.example.desafiopitang.util.GsonUtil
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieHistoryPresenter @Inject constructor() : MovieHistoryContract.Presenter {

    private lateinit var view: MovieHistoryContract.View

    override fun loadMovies(ctx : Context, key: String) {
        view.showProgress(true)
        val sharedPreferences = ctx.getSharedPreferences(Constants.prefKey, Constants.privateMode)

        val movies = GsonUtil.deserialize(key, sharedPreferences)
        if (movies.isNullOrEmpty()){
            view.onError("Lista vazia!")
        }
        else{
            view.getMovies(movies)
        }
        view.showProgress(false)
    }

    override fun subscribe() {}

    override fun unsubscribe() {}

    override fun attach(view: MovieHistoryContract.View) {
        this.view = view
    }
}