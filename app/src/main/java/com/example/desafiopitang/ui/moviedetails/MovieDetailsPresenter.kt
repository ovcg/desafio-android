package com.example.desafiopitang.ui.moviedetails

import com.example.desafiopitang.data.models.MovieDetails
import com.example.desafiopitang.network.ApiServiceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailsPresenter @Inject constructor() : MovieDetailContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: MovieDetailContract.View

    override fun subscribe() {}

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MovieDetailContract.View) {
        this.view = view
    }

    override fun getMovie(id: String) {
        view.showProgress(true)

        val subscribe = api.getMovie(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movie : MovieDetails? ->
                view.showProgress(false)
                view.getMovie(movie!!)
            }, { error ->
                view.showProgress(false)
                view.onError(error?.localizedMessage.toString())
            })

        subscriptions.add(subscribe)
    }

}