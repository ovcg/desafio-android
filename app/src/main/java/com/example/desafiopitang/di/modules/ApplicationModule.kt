package com.example.desafiopitang.di.modules

import android.app.Application
import com.example.desafiopitang.di.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule (private val app: App) {

    @Provides
    @Singleton

    fun provideApplication(): Application {
        return app
    }
}