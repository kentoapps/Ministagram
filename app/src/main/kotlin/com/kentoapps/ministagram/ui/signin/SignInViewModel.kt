package com.kentoapps.ministagram.ui.signin

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.kentoapps.ministagram.data.source.user.UserRepository
import com.kentoapps.ministagram.util.SingleLiveEvent
import com.kentoapps.ministagram.util.extension.isValidEmail
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SignInViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    val email = ObservableField<String>()
    val password = ObservableField<String>()

    val errorMessage = ObservableField<String>("")
    val successCommand = SingleLiveEvent<Void>()

    fun onSignInClick() {
        if (email.get().isNullOrBlank() || password.get().isNullOrBlank())
            errorMessage.set("Please fill out all fields")
        else if (!email.get().isValidEmail())
            errorMessage.set("Email is invalid")
        else
            repository.signIn(email.get()!!, password.get()!!)
                    .subscribeBy(
                            onComplete = { successCommand.call() },
                            onError = {
                                println("=== error: ${it.message}")
                                errorMessage.set(it.message)
                            })
    }
}
