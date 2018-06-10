package com.kentoapps.ministagram

import com.kentoapps.ministagram.di.applyAutoInjector
import com.kentoapps.ministagram.di.component.DaggerAppComponent
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

//    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
//        return DaggerAppComponent.builder().create(this)
//    }

    override fun applicationInjector() = DaggerAppComponent.builder()
            .application(this)
            .build()

    override fun onCreate() {
        super.onCreate()
        applyAutoInjector()
    }

}