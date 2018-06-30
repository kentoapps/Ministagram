package com.kentoapps.ministagram.di.module

import android.arch.lifecycle.ViewModel
import com.kentoapps.ministagram.di.ViewModelKey
import com.kentoapps.ministagram.ui.account.AccountViewModel
import com.kentoapps.ministagram.ui.account.SignInFragment
import com.kentoapps.ministagram.ui.account.SignUpFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AccountModule {
//    @Binds
//    @IntoMap
//    @ActivityKey(SignInActivity::class)
//    internal abstract fun bindInjectorFactory(builder: SignInActivityComponent.Builder): AndroidInjector.Factory<out Activity>
//
//    @ContributesAndroidInjector(modules = [SignInActivityModule::class])
//    internal abstract fun contributeSignInActivity(): SignInActivity

    @ContributesAndroidInjector
    abstract fun contributeSignInFragment(): SignInFragment

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindSignInViewModel(viewModel: AccountViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeSignUpFragment(): SignUpFragment
}
