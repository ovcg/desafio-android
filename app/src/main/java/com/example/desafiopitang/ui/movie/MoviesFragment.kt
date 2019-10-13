package com.example.desafiopitang.ui.movie

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
import com.example.desafiopitang.di.modules.FragmentModule
import com.example.desafiopitang.ui.adapter.MovieAdapter
import com.example.desafiopitang.ui.adapter.RecyclerItemDecoration
import com.example.desafiopitang.ui.moviedetails.MovieDetailsActivity
import com.example.desafiopitang.util.Constants
import com.example.desafiopitang.util.InternetUtil.isConnected
import com.example.desafiopitang.util.MsgUtil
import com.example.desafiopitang.util.MsgUtil.showMsg
import com.example.desafiopitang.util.interfaces.ClickMovieListener
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : Fragment() ,
    MovieContract.View,
    ClickMovieListener {

    private val adapter : MovieAdapter by lazy {
        MovieAdapter(context!!,  ArrayList(),this)
    }
    @Inject lateinit var presenter : MoviePresenter
    private lateinit var rootView : View
    private var page = 0
    private var size = 10

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

        if (savedInstanceState != null){
            page = savedInstanceState.getInt(Constants.page)
            size = savedInstanceState.getInt(Constants.size)
        }

        rv_movies.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
            addItemDecoration(RecyclerItemDecoration(10))

        }
    }

    override fun onResume() {
        super.onResume()
        context?.let {
            if (isConnected(it)) {
                presenter.loadMovies(page.toString(), size.toString())
            }
            else{
                showMsg(it,it.getString(R.string.internet_error), rootView, true)
            }
        }
    }

    override fun movieSelected(movie: Movie) {
        presenter.movieDetail(movie._id)
    }

    private fun injectDependency(){
        val activityComponent = DaggerActivityComponent.builder()
            .fragmentModule(FragmentModule()).build()
        activityComponent.inject(this)
    }

    override fun onSuccess(msg: String) {

    }

    override fun onError(msg: String) {
        showMsg(context!!,msg, rootView, true)
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
        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.putExtra(Constants.movieId, movie._id)
        startActivity(intent)
    }
}