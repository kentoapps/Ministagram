package com.kentoapps.ministagram.ui.user

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.kentoapps.ministagram.data.source.user.UserRepository
import com.kentoapps.ministagram.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    val errorMessage = ObservableField<String>("")
    val successCommand = SingleLiveEvent<Void>()

    private val disposables = CompositeDisposable()

    fun signOut() {
        repository.signOut()
                .subscribeBy(
                        onComplete = { successCommand.call() },
                        onError = { errorMessage.set(it.message) }
                ).addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
