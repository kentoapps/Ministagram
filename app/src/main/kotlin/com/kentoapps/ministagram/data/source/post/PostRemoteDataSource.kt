package com.kentoapps.ministagram.data.source.post

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.kentoapps.ministagram.data.model.Post
import com.kentoapps.ministagram.data.model.PostRequest
import com.kentoapps.ministagram.util.COLLECTION_POST
import io.reactivex.Completable
import io.reactivex.Observable

class PostRemoteDataSource : PostDataSource {
    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

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

    override fun savePost(post: PostRequest): Completable {
        // TODO It's done using WorkManager
        println("======= savePost")
        return Completable.create { emitter ->
            println("======== putFile")
            storage.reference.child(post.uri.lastPathSegment)
                    .putFile(post.uri)
                    .addOnSuccessListener { taskSnapshot ->
                        println("======= ${taskSnapshot.uploadSessionUri}")
                    }.addOnFailureListener { exception ->
                        println("======= ${exception.message}")
                    }
        }
    }

    override fun updatePost(post: Post): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletePost(id: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}