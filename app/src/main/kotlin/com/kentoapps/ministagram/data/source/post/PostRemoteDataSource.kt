package com.kentoapps.ministagram.data.source.post

import com.google.firebase.firestore.FirebaseFirestore
import com.kentoapps.ministagram.data.model.Post
import com.kentoapps.ministagram.util.COLLECTION_POST
import io.reactivex.Completable
import io.reactivex.Observable

class PostRemoteDataSource : PostDataSource {
    private val db = FirebaseFirestore.getInstance()

    override fun getPostList(): Observable<List<Post>> {
        return Observable.create { emitter ->
            db.collection(COLLECTION_POST)
                    .get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            emitter.onNext(task.result.map { it.toObject(Post::class.java) })
                        } else {
                            emitter.onError(Throwable("ERROR"))
                        }
                    }
        }
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