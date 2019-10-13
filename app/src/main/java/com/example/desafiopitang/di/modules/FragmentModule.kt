package com.example.desafiopitang.di.modules

import com.example.desafiopitang.network.ApiServiceInterface
import com.example.desafiopitang.ui.movie.MovieContract
import com.example.desafiopitang.ui.movie.MoviePresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideMoviePresenter() : MovieContract.Presenter = MoviePresenter()

    @Provides
    fun provideApi() : ApiServiceInterface = ApiServiceInterface.create()

}