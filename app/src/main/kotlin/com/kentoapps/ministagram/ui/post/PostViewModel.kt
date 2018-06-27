package com.kentoapps.ministagram.ui.post

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.net.Uri
import com.kentoapps.ministagram.data.model.PostRequest
import com.kentoapps.ministagram.data.source.post.PostRepository
import com.kentoapps.ministagram.data.source.user.UserRepository
import com.kentoapps.ministagram.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class PostViewModel @Inject constructor(
        private val repository: PostRepository, private val userRepository: UserRepository)
    : ViewModel() {

    val uri = ObservableField<Uri>()
    val caption = ObservableField<String>()

    val errorMessage = ObservableField<String>("")
    val successCommand = SingleLiveEvent<Void>()

    private val disposables = CompositeDisposable()

    fun savePost() {
        val uri = this.uri.get() ?: return
        val caption = this.caption.get() ?: return
        userRepository.getUser()
                .flatMapCompletable { user ->
                    repository.savePost(PostRequest(uri, caption, user.userId!!, user.userName!!, user.userImage!!))
                }
                .subscribeBy(
                        onComplete = {
                            this.caption.set("")
                            successCommand.call()
                        },
                        onError = { errorMessage.set(it.toString()) }
                ).addTo(disposables)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
