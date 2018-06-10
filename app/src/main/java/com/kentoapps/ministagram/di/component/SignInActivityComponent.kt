package com.kentoapps.ministagram.di.component

import com.kentoapps.ministagram.di.module.SignInModule
import com.kentoapps.ministagram.ui.SignInActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector


@Subcomponent(modules = [SignInModule::class])
interface SignInActivityComponent : AndroidInjector<SignInActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SignInActivity>()
}