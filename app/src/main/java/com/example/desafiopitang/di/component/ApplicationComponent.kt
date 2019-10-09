package com.example.desafiopitang.di.component

import com.example.desafiopitang.di.App
import com.example.desafiopitang.di.modules.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: App)

}