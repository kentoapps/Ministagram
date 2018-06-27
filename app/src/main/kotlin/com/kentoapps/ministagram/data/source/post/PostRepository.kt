package com.kentoapps.ministagram.data.source.post

import android.net.Uri
import com.kentoapps.ministagram.data.model.Post
import io.reactivex.Completable
import io.reactivex.Observable

interface PostRepository {
    fun getPostList(): Observable<List<Post>>
    fun getPost(id: String): Observable<Post>
    fun savePost(uri: Uri, caption: String): Completable
    fun updatePost(post: Post): Completable
    fun deletePost(id: String): Completable
}