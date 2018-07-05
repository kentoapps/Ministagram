package com.kentoapps.ministagram.ui.comment

import android.arch.lifecycle.ViewModel;
import com.kentoapps.ministagram.data.source.post.PostRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CommentViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {

    private val disposables = CompositeDisposable()

    fun getCommentList(id: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
