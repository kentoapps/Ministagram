package com.kentoapps.ministagram.di.module

import com.kentoapps.ministagram.data.source.post.PostDataSource
import com.kentoapps.ministagram.data.source.post.PostRemoteDataSource
import com.kentoapps.ministagram.data.source.post.PostRepository
import com.kentoapps.ministagram.data.source.post.PostRepositoryImpl
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

    @Singleton
    @Provides
    @JvmStatic
    fun providePostRepository(dataSource: PostDataSource): PostRepository = PostRepositoryImpl(dataSource)

    @Singleton
    @Provides
    @JvmStatic
    fun providePostDataSource(): PostDataSource = PostRemoteDataSource()
}
