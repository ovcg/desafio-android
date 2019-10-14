package com.example.desafiopitang.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.desafiopitang.R
import com.example.desafiopitang.di.component.DaggerActivityComponent
import com.example.desafiopitang.ui.moviehistory.HistoryListFragment
import com.example.desafiopitang.ui.movie.MoviesFragment
import com.example.desafiopitang.util.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject lateinit var presenter: MainPresenter
    private lateinit var navView : BottomNavigationView
    private val mOnNavigationItemSelectedListener = BottomNavigationView
                                                    .OnNavigationItemSelectedListener { item ->

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
        }

        when (item.itemId){
            R.id.navigation_home -> {
                this.showMoviesFragment()
                return@OnNavigationItemSelectedListener true
            }
            else -> {
                this.showMovieHistory()
                return@OnNavigationItemSelectedListener true
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependency()
        presenter.attach(this)

        navView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState != null){
            when(savedInstanceState.getInt(Constants.fragmentId)){
                R.id.navigation_home -> this.showMoviesFragment()
                else -> this.showMovieHistory()
            }
        }
        else {
            this.showMoviesFragment()
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

    override fun showMoviesFragment() {
        replaceFragment(MoviesFragment(), "MoviesFragment")
    }

    override fun showMovieHistory() {
        replaceFragment(HistoryListFragment(), "HistoryListFragment")
    }

    private fun replaceFragment(fragment: Fragment, tagFragmentName: String) {

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        val currentFragment = fm.primaryNavigationFragment
        if (currentFragment != null) {
            ft.detach(currentFragment)
        }

        var fragmentTemp = fm.findFragmentByTag(tagFragmentName)

        if (fragmentTemp == null) {
            fragmentTemp = fragment
            ft.add(R.id.content, fragmentTemp, tagFragmentName)
        } else {
            ft.attach(fragmentTemp)
        }

        ft.setPrimaryNavigationFragment(fragmentTemp)
        ft.setReorderingAllowed(true)
        ft.commitNowAllowingStateLoss()
    }
}
