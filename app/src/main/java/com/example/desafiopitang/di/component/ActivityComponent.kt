package com.example.desafiopitang.di.component

import com.example.desafiopitang.di.modules.MovieModule
import com.example.desafiopitang.ui.movie.MovieActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MovieModule::class])
interface ActivityComponent {
    fun inject(activity: MovieActivity)
}