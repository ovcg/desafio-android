package com.example.desafiopitang.ui.movie

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.example.desafiopitang.R
import com.example.desafiopitang.data.models.Movie
import com.example.desafiopitang.di.modules.MovieModule
import javax.inject.Inject

class MovieActivity : AppCompatActivity(), MovieContract.View {

    @Inject lateinit var presenter: MoviePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        presenter.attach(this)
        presenter.getContext(this)
    }

    override fun onSuccess(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun moviesResponse(movies: List<Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun movieDetail(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
