package com.kentoapps.ministagram.di.module

import android.arch.lifecycle.ViewModel
import com.kentoapps.ministagram.di.ViewModelKey
import com.kentoapps.ministagram.ui.signup.SignUpFragment
import com.kentoapps.ministagram.ui.signup.SignUpViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class SignInModule {
//    @Binds
//    @IntoMap
//    @ActivityKey(SignInActivity::class)
//    internal abstract fun bindInjectorFactory(builder: SignInActivityComponent.Builder): AndroidInjector.Factory<out Activity>

//    @ContributesAndroidInjector(modules = [SignInActivityModule::class])
//    internal abstract fun contributeSignInActivity(): SignInActivity

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindMainViewModel(viewModel: SignUpViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeSignUpFragment(): SignUpFragment
}
