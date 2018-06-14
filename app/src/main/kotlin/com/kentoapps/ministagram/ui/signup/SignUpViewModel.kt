package com.kentoapps.ministagram.ui.signup

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.kentoapps.ministagram.data.source.user.UserDataSource
import com.kentoapps.ministagram.util.SingleLiveEvent
import com.kentoapps.ministagram.util.extension.isValidEmail
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val repository: UserDataSource) : ViewModel() {
    val userName = ObservableField<String>()
    val email = ObservableField<String>()
    val password = ObservableField<String>()
    val confirm = ObservableField<String>()

    val errorMessage = ObservableField<String>()
    val successCommand = SingleLiveEvent<Void>()

    fun onSignUpClick() {
        if (userName.get().isNullOrBlank() || email.get().isNullOrBlank() || password.get().isNullOrBlank() || confirm.get().isNullOrBlank())
            errorMessage.set("Please fill out all fields")
        else if (!email.get().isValidEmail())
            errorMessage.set("Email is invalid")
        else if (password.get() != confirm.get())
            errorMessage.set("Password does not match the confirm password")
        else
            repository.signUp(userName.get()!!, email.get()!!, password.get()!!)
                    .subscribeBy(
                            onComplete = { successCommand.call() },
                            onError = {
                                println("=== error: ${it.message}")
                                errorMessage.set(it.message)
                            })
    }
}
