package com.example.desafiopitang.di.component

import com.example.desafiopitang.di.modules.FragmentModule
import com.example.desafiopitang.di.modules.MainModule
import com.example.desafiopitang.ui.main.MainActivity
import com.example.desafiopitang.ui.moviedetails.MovieDetailsActivity
import com.example.desafiopitang.ui.movie.MoviesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class, FragmentModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: MoviesFragment)
    fun inject(activity: MovieDetailsActivity)

}