package com.example.desafiopitang.di.modules

import android.app.Application
import com.example.desafiopitang.App
import com.example.desafiopitang.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule (private val app: App) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application = app
}