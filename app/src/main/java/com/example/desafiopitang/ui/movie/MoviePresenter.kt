package com.example.desafiopitang.ui.movie

import android.content.Context
import com.example.desafiopitang.data.models.Movie
import com.example.desafiopitang.network.ApiServiceInterface
import com.example.desafiopitang.util.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviePresenter : MovieContract.Presenter{

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create(Constants.baseUrl)
    private lateinit var view: MovieContract.View
    private var url = ""
    lateinit var interactMovie :  MovieContract.GetMovieInteractor

    constructor()

    @Inject constructor(interactor: MovieContract.GetMovieInteractor){
        this.interactMovie = interactor
    }

    fun setUrl(url:String){
        this.url = url
    }

    override fun subscribe() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unsubscribe() {
       subscriptions.clear()
    }

    override fun attach(view: MovieContract.View) {
        this.view = view
    }

    override fun getContext(context: Context) {}

    override fun loadMovies() {
        view.showProgress(true)

        val subscribe = api.getMovieList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: List<Movie>? ->
                view.showProgress(false)
                view.moviesResponse(list!!.take(10))
            }, { error ->
                view.showProgress(false)
                view.onError(error?.localizedMessage.toString())

            })

        subscriptions.add(subscribe)
    }

    override fun movieDetail(id: String) {
        view.showProgress(true)

        val subscribe = api.getMovie().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ movie : Movie? ->
                view.showProgress(false)
                view.movieDetail(movie!!)
            }, { error ->
                view.showProgress(false)
                view.onError(error?.localizedMessage.toString())

            })

        subscriptions.add(subscribe)
    }

}