package com.kentoapps.ministagram.di.module

import com.kentoapps.ministagram.data.source.user.UserDataSource
import com.kentoapps.ministagram.data.source.user.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {
    @Singleton
    @Provides
    @JvmStatic
    fun provideUserDataSource(): UserDataSource = UserRemoteDataSource()
}
