package com.example.desafiopitang.di

import android.app.Application
import com.example.desafiopitang.BuildConfig
import com.example.desafiopitang.di.component.ApplicationComponent
import com.example.desafiopitang.di.modules.ApplicationModule
import dagger.android.DaggerApplication

class App : Application() {

        lateinit var component: ApplicationComponent

        override fun onCreate() {
            super.onCreate()

            instance = this
            setup()
        }

        private fun setup() {
            component =  DaggerApplicationComponent.builder()
                        .applicationModule(ApplicationModule(this)).build()
            component.inject(this)
        }

        fun getApplicationComponent(): ApplicationComponent {
            return component
        }

        companion object {
            lateinit var instance: App private set
        }
    }
}