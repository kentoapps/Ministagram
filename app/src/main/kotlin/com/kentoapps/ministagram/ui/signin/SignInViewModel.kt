package com.kentoapps.ministagram.ui.signin

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.kentoapps.ministagram.data.source.user.UserDataSource
import com.kentoapps.ministagram.util.extension.isValidEmail
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val repository: UserDataSource) : ViewModel() {
    val email = ObservableField<String>()
    val password = ObservableField<String>()

    val errorMessage = ObservableField<String>()

    fun onSignInClick() {
        if (email.get().isNullOrBlank() || password.get().isNullOrBlank())
            errorMessage.set("Please fill out all fields")
        else if (!email.get().isValidEmail())
            errorMessage.set("Email is invalid")
        else
            repository.signIn(email.get()!!, password.get()!!)
                    .subscribeBy(
                            onComplete = { println("=== complete") },
                            onError = {
                                println("=== error: ${it.message}")
                                errorMessage.set(it.message)
                            })
    }
}