package com.kentoapps.ministagram.di.module

import android.arch.lifecycle.ViewModelProvider
import com.kentoapps.ministagram.di.ViewModelFactory
import com.kentoapps.ministagram.ui.AccountActivity
import com.kentoapps.ministagram.ui.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = [AccountModule::class])
    internal abstract fun contributeAccountActivity(): AccountActivity

    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}