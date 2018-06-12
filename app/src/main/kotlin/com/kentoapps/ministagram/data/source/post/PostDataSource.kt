package com.kentoapps.ministagram.data.source.post

import com.kentoapps.ministagram.data.model.Post
import io.reactivex.Completable
import io.reactivex.Observable

interface PostDataSource {
    fun getPostList(): Observable<List<Post>>
    fun getPost(id: String): Observable<Post>
    fun savePost(post: Post): Completable
    fun updatePost(post: Post): Completable
    fun deletePost(id: String): Completable
}