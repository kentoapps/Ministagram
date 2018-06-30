package com.kentoapps.ministagram.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import androidx.navigation.Navigation
import com.kentoapps.ministagram.R
import com.kentoapps.ministagram.ui.account.AccountViewModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class AccountActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(AccountViewModel::class.java)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        viewModel.successCommand.observe(this, Observer {
            Navigation.findNavController(this, R.id.navHostFragment).navigate(R.id.main_activity)
            finish()
        })
        viewModel.isSignIn()
    }
}
