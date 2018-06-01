package com.kentoapps.ministagram.ui.signup

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.kentoapps.ministagram.data.source.user.UserRepository
import io.reactivex.rxkotlin.subscribeBy

class SignUpViewModel : ViewModel() {
    val userName = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val confirm = ObservableField<String>()

    fun onSignUpClick() {
        // TODO Should be injected by Dagger
        val repository = UserRepository()
        // TODO Validate values
        repository.signUp(userName.get()!!, email.get()!!, password.get()!!)
                .subscribeBy(
                        onComplete = {
                            println("=== complete")
                        },
                        onError = {
                            println("=== error: ${it.message}")
                        }
                )
    }
}
