package com.kentoapps.ministagram.data.source.post

import com.kentoapps.ministagram.data.model.Comment
import com.kentoapps.ministagram.data.model.Post
import com.kentoapps.ministagram.data.model.PostRequest
import com.kentoapps.ministagram.data.model.User
import io.reactivex.Completable
import io.reactivex.Observable

interface PostDataSource {
    fun getPostList(): Observable<List<Post>>
    fun getPost(id: String): Observable<Post>
    fun savePost(pr: PostRequest): Completable
    fun updatePost(post: Post): Completable
    fun deletePost(id: String): Completable
    fun updateLike(id: String, users: List<User>): Completable
    fun getCommentList(postId: String): Observable<List<Comment>>
    fun saveComment(postId: String, comment: Comment): Completable
}