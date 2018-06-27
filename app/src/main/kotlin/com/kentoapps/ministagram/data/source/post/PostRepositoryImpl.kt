package com.kentoapps.ministagram.data.source.post

import android.net.Uri
import com.kentoapps.ministagram.data.model.Post
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val dataSource: PostDataSource) : PostRepository {
    override fun getPostList(): Observable<List<Post>> {
        return dataSource.getPostList()
    }

    override fun getPost(id: String): Observable<Post> {
        return dataSource.getPost(id)
    }

    override fun savePost(uri: Uri, caption: String): Completable {
        return dataSource.savePost(uri, caption)
    }

    override fun updatePost(post: Post): Completable {
        return dataSource.updatePost(post)
    }

    override fun deletePost(id: String): Completable {
        return dataSource.deletePost(id)
    }
}