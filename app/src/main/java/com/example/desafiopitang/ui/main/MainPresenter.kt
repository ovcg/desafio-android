package com.example.desafiopitang.ui.main

import com.example.desafiopitang.network.ApiServiceInterface
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor(): MainContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: MainContract.View

    override fun onBottomNavClick() {

    }

    override fun subscribe() {}

    override fun unsubscribe() {

    }

    override fun attach(view: MainContract.View) {
        this.view = view
    }
}