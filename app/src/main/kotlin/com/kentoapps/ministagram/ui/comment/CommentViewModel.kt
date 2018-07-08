package com.kentoapps.ministagram.ui.comment

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import com.kentoapps.ministagram.data.model.Comment
import com.kentoapps.ministagram.data.source.post.PostRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class CommentViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    val comments = MutableLiveData<List<Comment>>()

    private val disposables = CompositeDisposable()

    fun getCommentList(id: String) {
        repository.getCommentList(id)
                .subscribeBy(
                        onNext = { comments.postValue(it) },
                        onError = { println("===== comment error") }
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
