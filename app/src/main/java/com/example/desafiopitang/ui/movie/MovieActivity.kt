package com.example.desafiopitang.ui.movie

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopitang.R
import com.example.desafiopitang.data.models.Movie
import com.example.desafiopitang.di.component.DaggerActivityComponent
import com.example.desafiopitang.di.modules.MovieModule
import com.example.desafiopitang.ui.adapter.MovieAdapter
import com.example.desafiopitang.util.interfaces.ClickMovieListener
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MovieActivity : AppCompatActivity(),
    MovieContract.View,
    ClickMovieListener {

    private val adapter : MovieAdapter by lazy {
        MovieAdapter(this,  ArrayList(),this)
    }
    @Inject lateinit var presenter: MoviePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependency()
        presenter.attach(this)
        presenter.getContext(this)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        rv_movies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }
        presenter.loadMovies("0","10")

    }

    override fun movieSelected(movie: Movie) {

    }

    private fun injectDependency(){
        val activityComponent = DaggerActivityComponent.builder()
                                .movieModule(MovieModule(this)).build()
        activityComponent.inject(this)
    }

    override fun onSuccess(msg: String) {

    }

    override fun onError(msg: String) {

    }

    override fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) {View.VISIBLE
                                 } else {View.GONE }
    }

    override fun moviesResponse(movies: List<Movie>) {
       adapter.setList(movies as ArrayList<Movie>)
       rv_movies.adapter = adapter
    }

    override fun movieDetail(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
