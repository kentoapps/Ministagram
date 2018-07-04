package com.kentoapps.ministagram.ui.timeline

import android.arch.lifecycle.ViewModel
import com.kentoapps.ministagram.data.model.Post
import com.kentoapps.ministagram.data.source.post.PostRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class TimelineViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {
    val posts: BehaviorSubject<List<Post>> = BehaviorSubject.create()

    private val disposables = CompositeDisposable()

    fun getPostList() {
        repository.getPostList()
                .subscribeBy(
                        onNext = { posts.onNext(it) },
                        onError = { println("=== Error: [getPostList] ${it.message}") }
                ).addTo(disposables)
    }

    fun updateLike(id: String) {
        val post = posts.value?.first { it.id == id } ?: return
        println("===== ${post.isLike}")
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
