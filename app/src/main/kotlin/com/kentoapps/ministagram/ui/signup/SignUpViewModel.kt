package com.kentoapps.ministagram.ui.signup

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.kentoapps.ministagram.data.source.user.UserRepository
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    val userName = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val confirm = ObservableField<String>()

    fun onSignUpClick() {
        // TODO Validate values
        repository.signUp(userName.get()!!, email.get()!!, password.get()!!)
                .subscribeBy(
                        onComplete = { println("=== complete") },
                        onError = { println("=== error: ${it.message}") })
    }
}
