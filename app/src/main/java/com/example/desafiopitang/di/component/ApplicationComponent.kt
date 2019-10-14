package com.example.desafiopitang.di.component

import com.example.desafiopitang.App
import com.example.desafiopitang.di.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(app: App)
}