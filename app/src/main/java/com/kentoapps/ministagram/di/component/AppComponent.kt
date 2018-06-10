package com.kentoapps.ministagram.di.component

import com.kentoapps.ministagram.App
import com.kentoapps.ministagram.di.module.ActivityModule
import com.kentoapps.ministagram.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityModule::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder
        fun build(): AppComponent
    }

//    @Component.Builder
//    abstract class Builder : AndroidInjector.Builder<App>()

    override fun inject(app: App)
}