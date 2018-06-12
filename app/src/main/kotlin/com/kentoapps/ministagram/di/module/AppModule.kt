package com.kentoapps.ministagram.di.module

import dagger.Module

@Module(includes = [DataModule::class])
internal object AppModule {
//    @Singleton
//    @Provides
//    @JvmStatic
//    fun provideAppLifecycleCallbacks(): AppLifecycleCallbacks = DebugAppLifecycleCallbacks()
}