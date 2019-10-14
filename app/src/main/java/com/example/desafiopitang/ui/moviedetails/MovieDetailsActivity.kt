package com.example.desafiopitang.ui.moviedetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.desafiopitang.R
import com.example.desafiopitang.data.models.MovieDetails
import com.example.desafiopitang.di.component.DaggerActivityComponent
import com.example.desafiopitang.ui.movie.MovieContract
import com.example.desafiopitang.util.Constants
import com.example.desafiopitang.util.ImgUtil
import com.example.desafiopitang.util.InternetUtil
import com.example.desafiopitang.util.MsgUtil
import kotlinx.android.synthetic.main.activity_movie_details.*
import javax.inject.Inject

class MovieDetailsActivity : AppCompatActivity(), MovieDetailContract.View{

    @Inject lateinit var presenter : MovieDetailsPresenter
    private lateinit var rootView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        rootView = findViewById(R.id.cl_content)

        val id = intent.getStringExtra(Constants.movieId)?:""

        injectDependency()
        presenter.attach(this)
        presenter.getMovie(id)
    }

    override fun onResume() {
        super.onResume()
        if (!InternetUtil.isConnected(this)) {
            this.onError(getString(R.string.internet_error))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    private fun injectDependency(){
        val activityComponent = DaggerActivityComponent.builder().build()
        activityComponent.inject(this)
    }

    override fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) {View.VISIBLE
        } else {View.GONE }
    }

    override fun onError(msg: String) {
        MsgUtil.showMsg( msg, rootView)
    }

    override fun getMovie(movieDetails: MovieDetails) {
        val title = "Title: \n${movieDetails.name}"
        val desc = "Description: \n${movieDetails.description}"

        tv_movie_name.text = title
        tv_desc.text = desc
        ImgUtil.loadImgBig(this, movieDetails.url, iv_detail)
    }
}
