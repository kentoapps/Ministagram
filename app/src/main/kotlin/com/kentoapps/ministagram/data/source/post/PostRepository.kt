package com.kentoapps.ministagram.data.source.post

import com.kentoapps.ministagram.data.model.Post
import com.kentoapps.ministagram.data.model.PostRequest
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

interface PostRepository {
    fun getPostList(): Observable<List<Post>>
    fun getPost(id: String): Observable<Post>
    fun savePost(post: PostRequest): Completable
    fun updatePost(post: Post): Completable
    fun deletePost(id: String): Completable
}

class PostRepositoryImpl @Inject constructor(private val dataSource: PostDataSource) : PostRepository {
    override fun getPostList(): Observable<List<Post>> {
        return dataSource.getPostList()
    }

    override fun getPost(id: String): Observable<Post> {
        return dataSource.getPost(id)
    }

    override fun savePost(post: PostRequest): Completable {
        return dataSource.savePost(post)
    }

    override fun updatePost(post: Post): Completable {
        return dataSource.updatePost(post)
    }

    override fun deletePost(id: String): Completable {
        return dataSource.deletePost(id)
    }
}