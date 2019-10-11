package com.example.desafiopitang.di.modules

import com.example.desafiopitang.ui.movie.MovieContract
import com.example.desafiopitang.ui.movie.MovieInteractorImpl
import com.example.desafiopitang.ui.movie.MoviePresenter
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @Provides
    fun providesMovieInteractor() : MovieContract.GetMovieInteractor = MovieInteractorImpl()

    @Provides
    fun providesPresenter() : MovieContract.Presenter = MoviePresenter()
}