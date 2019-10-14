package com.example.desafiopitang.ui.movie

import com.example.desafiopitang.data.models.Movie
import com.example.desafiopitang.network.ApiServiceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviePresenter @Inject constructor() : MovieContract.Presenter{

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: MovieContract.View

    override fun subscribe() {}

    override fun unsubscribe() {
       subscriptions.clear()
    }

    override fun attach(view: MovieContract.View) {
        this.view = view
    }

    override fun loadMovies(page: String, size:String) {
        view.showProgress(true)

        val subscribe = api.getMovieList(page,size).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: ArrayList<Movie>? ->
                view.showProgress(false)
                view.moviesResponse(list!!)
            }, { error ->
                view.showProgress(false)
                view.onError(error?.localizedMessage.toString())
            })

        subscriptions.add(subscribe)
    }
}