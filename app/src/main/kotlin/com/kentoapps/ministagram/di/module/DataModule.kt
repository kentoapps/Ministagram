package com.kentoapps.ministagram.di.module

import com.kentoapps.ministagram.data.source.user.UserDataSource
import com.kentoapps.ministagram.data.source.user.UserRemoteDataSource
import com.kentoapps.ministagram.data.source.user.UserRepository
import com.kentoapps.ministagram.data.source.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DataModule {
    @Singleton
    @Provides
    @JvmStatic
    fun provideUserRepository(dataSource: UserDataSource): UserRepository = UserRepositoryImpl(dataSource)

    @Singleton
    @Provides
    @JvmStatic
    fun provideUserDataSource(): UserDataSource = UserRemoteDataSource()
}
