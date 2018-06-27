package com.kentoapps.ministagram.data.source.post

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.kentoapps.ministagram.data.model.Post
import com.kentoapps.ministagram.data.model.PostRequest
import com.kentoapps.ministagram.util.COLLECTION_POST
import com.kentoapps.ministagram.util.STORAGE_POSTS
import io.reactivex.Completable
import io.reactivex.Observable
import java.util.*

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
        // TODO It should be done using WorkManager
        return Completable.create { emitter ->
            val fileName = "${post.uri.lastPathSegment}_${Date()}"
            println("===== before ${post.userId} ${post.userName} $fileName")
            storage.reference
                    .child("$STORAGE_POSTS/${post.userId}/$fileName")
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