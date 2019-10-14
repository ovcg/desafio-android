package com.example.desafiopitang.ui.moviehistory

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafiopitang.R
import com.example.desafiopitang.data.models.Movie
import com.example.desafiopitang.di.component.DaggerActivityComponent
import com.example.desafiopitang.ui.adapter.MovieAdapter
import com.example.desafiopitang.ui.adapter.RecyclerItemDecoration
import com.example.desafiopitang.ui.moviedetails.MovieDetailsActivity
import com.example.desafiopitang.util.Constants
import com.example.desafiopitang.util.GsonUtil
import com.example.desafiopitang.util.MsgUtil
import com.example.desafiopitang.util.interfaces.ClickMovieListener
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class HistoryListFragment : Fragment() ,
    MovieHistoryContract.View,
    ClickMovieListener {

    private val adapter : MovieAdapter by lazy {
        MovieAdapter(context!!,  ArrayList(),this)
    }
    @Inject lateinit var presenter : MovieHistoryPresenter
    private lateinit var rootView : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_movies, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectDependency()
        presenter.attach(this)

        context?.let { presenter.loadMovies(it, Constants.moviesList)}

        rv_movies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
            addItemDecoration(RecyclerItemDecoration(10))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun movieSelected(movie: Movie) {
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.putExtra(Constants.movieId, movie._id)
        startActivity(intent)
    }

    private fun injectDependency(){
        val activityComponent = DaggerActivityComponent.builder().build()
        activityComponent.inject(this)
    }

    override fun getMovies(movies: List<Movie>) {
        adapter.setList(movies as ArrayList<Movie>)
        rv_movies.adapter = adapter
    }

    override fun onError(msg: String) {
        MsgUtil.showMsg(context!!, msg, rootView, true)
    }

    override fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(Constants.fragmentId, R.id.nav_history)
    }

}