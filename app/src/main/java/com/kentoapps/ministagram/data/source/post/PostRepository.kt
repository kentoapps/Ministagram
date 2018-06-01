package com.kentoapps.ministagram.data.source.post

import com.kentoapps.ministagram.data.model.Post
import io.reactivex.Completable
import io.reactivex.Observable

class PostRepository(private val dataSource: PostDataSource) : PostDataSource {
    override fun getPostList(): Observable<List<Post>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPost(id: String): Observable<Post> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun savePost(post: Post): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updatePost(post: Post): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletePost(id: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}