package com.kentoapps.ministagram.ui.timeline

import android.arch.lifecycle.ViewModel
import com.kentoapps.ministagram.data.model.Post
import com.kentoapps.ministagram.data.source.post.PostRepository
import com.kentoapps.ministagram.data.source.user.UserRepository
import com.kentoapps.ministagram.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class TimelineViewModel @Inject constructor(
        private val repository: PostRepository,
        private val userRepository: UserRepository) : ViewModel() {

    val posts: BehaviorSubject<List<Post>> = BehaviorSubject.create()
    val openCommentEvent = SingleLiveEvent<String>()

    private val disposables = CompositeDisposable()

    fun getPostList() {
        Observables.combineLatest(
                repository.getPostList(),
                userRepository.getUser())
        { pl, u ->
            pl.map { p ->
                p.apply { if (likeUsers.contains(u)) isLike = true }
            }
        }.subscribeBy(
                onNext = { posts.onNext(it) },
                onError = { println("=== Error: [getPostList] ${it.message}") }
        ).addTo(disposables)
    }

    fun updateLike(id: String, updateLikeNumText: () -> Unit) {
        val post = posts.value?.first { it.id == id } ?: return
        userRepository.getUser()
                .flatMapCompletable { user ->
                    if (post.isLike) post.likeUsers.add(user)
                    else post.likeUsers.remove(user)
                    updateLikeNumText()

                    repository.updateLike(id, post.likeUsers)
                }.subscribeBy(
                        onComplete = { println("=== onComplete updateLike!") },
                        onError = { println("=== onError updateLike $it") }
                ).addTo(disposables)
    }

    fun onCommentClick(id: String?) {
        openCommentEvent.value = id
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
