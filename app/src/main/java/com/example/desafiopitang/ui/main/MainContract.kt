package com.example.desafiopitang.ui.main

import com.example.desafiopitang.ui.base.BaseContract

interface MainContract {

    interface View : BaseContract.View{
        fun showMoviesFragment()
        fun showMovieHistory()
    }

    interface Presenter : BaseContract.Presenter<View>{
        fun onBottomNavClick()
    }
}