package com.kentoapps.ministagram.ui.timeline

import android.arch.lifecycle.ViewModel
import com.kentoapps.ministagram.data.model.Post
import com.kentoapps.ministagram.data.source.post.PostRepositoryImpl
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class TimelineViewModel @Inject constructor(repository: PostRepositoryImpl): ViewModel() {
    val posts: BehaviorSubject<List<Post>> = BehaviorSubject.create()
}
