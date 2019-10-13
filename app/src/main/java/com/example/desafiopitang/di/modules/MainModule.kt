package com.example.desafiopitang.di.modules

import android.app.Activity
import com.example.desafiopitang.ui.main.MainContract
import com.example.desafiopitang.ui.main.MainPresenter
import com.example.desafiopitang.ui.movie.MovieContract
import com.example.desafiopitang.ui.movie.MoviePresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providesPresenter() : MainContract.Presenter = MainPresenter()
}