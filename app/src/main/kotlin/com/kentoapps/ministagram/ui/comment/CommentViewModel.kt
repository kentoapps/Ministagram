package com.kentoapps.ministagram.ui.comment

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.kentoapps.ministagram.data.model.Comment
import com.kentoapps.ministagram.data.source.post.PostRepository
import com.kentoapps.ministagram.data.source.user.UserRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class CommentViewModel @Inject constructor(private val repository: PostRepository, private val userRepository: UserRepository) : ViewModel() {

    val comments = MutableLiveData<List<Comment>>()
    val commentEdit = ObservableField<String>()

    val errorMessage = ObservableField<String>("")

    var postId: String? = null

    private val disposables = CompositeDisposable()

    fun getCommentList() {
        val postId = postId
        if (postId == null) {
            errorMessage.set("Failed to get comment list")
            return
        }

        repository.getCommentList(postId)
                .subscribeBy(
                        onNext = { comments.postValue(it) },
                        onError = { println("===== comment error") }
                ).addTo(disposables)
    }

    fun onSendClick() {
        val postId = postId
        if (postId == null) {
            errorMessage.set("Failed to save comment")
            return
        }

        val comment = commentEdit.get()
        if (comment == null) {
            errorMessage.set("Input comment")
            return
        }

        userRepository.getUser()
                .flatMapCompletable { user ->
                    repository.saveComment(postId, Comment(user, comment))
                }.subscribeBy(
                        onComplete = {
                            commentEdit.set("")
                            getCommentList()
                        },
                        onError = { println("====== saveComment onError") }
                ).addTo(disposables)
    }

    fun clear() {
        comments.value = emptyList()
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
