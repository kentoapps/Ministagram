package com.kentoapps.ministagram

import com.kentoapps.ministagram.di.AutoInjector
import com.kentoapps.ministagram.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree



class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        AutoInjector.init(this)

        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
    }
}
