package com.kentoapps.ministagram.ui.post

import android.arch.lifecycle.ViewModel
import com.kentoapps.ministagram.data.source.post.PostRepository
import javax.inject.Inject

class PostViewModel @Inject constructor(private val repository: PostRepository): ViewModel()  {
}
