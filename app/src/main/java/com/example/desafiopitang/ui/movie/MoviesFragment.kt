package com.example.desafiopitang.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiopitang.R
import com.example.desafiopitang.data.models.Movie
import com.example.desafiopitang.di.component.DaggerActivityComponent
import com.example.desafiopitang.ui.adapter.MovieAdapter
import com.example.desafiopitang.ui.adapter.RecyclerItemDecoration
import com.example.desafiopitang.ui.moviedetails.MovieDetailsActivity
import com.example.desafiopitang.util.Constants
import com.example.desafiopitang.util.GsonUtil
import com.example.desafiopitang.util.InternetUtil.isConnected
import com.example.desafiopitang.util.MsgUtil.showMsg
import com.example.desafiopitang.util.interfaces.ClickMovieListener
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : Fragment() ,
    MovieContract.View,
    ClickMovieListener {

    private val listMovies = ArrayList<Movie>()
    private val mvAdapter : MovieAdapter by lazy {
        MovieAdapter(context!!,  listMovies,this)
    }
    @Inject lateinit var presenter : MoviePresenter
    private lateinit var rootView : View
    private var page = Constants.defaultPage
    private var size = Constants.defaultSize
    private var isLoading = false
    private var increment = false

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
            adapter = mvAdapter
            addItemDecoration(RecyclerItemDecoration(10))
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = linearLayoutManager.itemCount
                    val lastVisible = linearLayoutManager.findLastVisibleItemPosition()
                    if (!isLoading && totalItemCount == lastVisible + 1) {
                        isLoading = true
                        resizeList()
                        presenter.loadMovies((page).toString(), size.toString())
                    }
                }
            })
        }
    }

    fun resizeList(){
        when {
            increment -> size += Constants.defaultSize
            else -> {
                ++page
                size = Constants.reducedSize
            }
        }
    }

    override fun onResume() {
        super.onResume()
        context?.let {
            if (isConnected(it)) {
                presenter.loadMovies(page.toString(), size.toString())
            }
            else{
                showMsg(it.getString(R.string.internet_error), rootView)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun movieSelected(movie: Movie) {
        val sharedPref = context?.getSharedPreferences(Constants.prefKey, Constants.privateMode)
        sharedPref?.let {
            var movies = GsonUtil.deserialize(Constants.moviesList, it)
            if (movies.isNullOrEmpty()) {
                movies = ArrayList()
            }
            if (!movies.contains(movie)) {
                movies.add(movie)
                GsonUtil.serialize(Constants.moviesList, sharedPref, movies)
            }
        }

        val intent = Intent(context, MovieDetailsActivity::class.java)
        intent.putExtra(Constants.movieId, movie._id)
        startActivity(intent)
    }

    private fun injectDependency(){
        val activityComponent = DaggerActivityComponent.builder().build()
        activityComponent.inject(this)
    }

    override fun onSuccess(msg: String) {}

    override fun onError(msg: String) {
        showMsg(msg, rootView)
    }

    override fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) {View.VISIBLE
        } else {View.GONE }
    }

    override fun moviesResponse(movies: List<Movie>) {

        increment = movies.isNotEmpty() && !included(movies[0]._id)

        val lastPos = this.listMovies.size
        this.listMovies.addAll(movies)

        if (page > 0) {
            rv_movies.adapter?.notifyItemRangeChanged(lastPos, movies.size)
        } else {
            mvAdapter.setList(movies as ArrayList<Movie>)
            rv_movies.adapter = mvAdapter
        }

        isLoading = false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(Constants.fragmentId, R.id.navigation_home)
        outState.putInt(Constants.page, page)
        outState.putInt(Constants.size, size)
    }

    private fun included(id : String) : Boolean{
        var contains = false
        for (movie in this.listMovies){
            if (movie._id == id){
                contains = true
            }
        }
        return contains
    }
}