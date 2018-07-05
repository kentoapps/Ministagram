package com.kentoapps.ministagram.data.source.post

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.kentoapps.ministagram.data.model.Post
import com.kentoapps.ministagram.data.model.PostRequest
import com.kentoapps.ministagram.data.model.User
import com.kentoapps.ministagram.util.COLLECTION_POST
import com.kentoapps.ministagram.util.FIELD_LIKE_USERS
import com.kentoapps.ministagram.util.STORAGE_POSTS
import io.reactivex.Completable
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*

class PostRemoteDataSource : PostDataSource {
    private val db = FirebaseFirestore.getInstance()

    private val storage = FirebaseStorage.getInstance()
    override fun getPostList(): Observable<List<Post>> {
        return Observable.create { emitter ->
            db.collection(COLLECTION_POST)
                    .orderBy("date", Query.Direction.DESCENDING)
                    .get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val posts = task.result.map { result ->
                                val post = result.toObject(Post::class.java)
                                post.apply { id = result.id }
                            }
                            emitter.onNext(posts)
                        } else {
                            emitter.onError(Throwable("ERROR"))
                        }
                    }
        }
    }

    override fun getPost(id: String): Observable<Post> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun savePost(pr: PostRequest): Completable {
        // TODO It should be done using WorkManager
        return Completable.create { emitter ->
            val fileName = "${pr.uri.lastPathSegment}_${SimpleDateFormat("yyyyMMdd_HHmm").format(Date())}"
            val filePath = storage.getReference("$STORAGE_POSTS/${pr.userId}").child(fileName)
            filePath.putFile(pr.uri)
                    .addOnSuccessListener {
                        filePath.downloadUrl.addOnSuccessListener { uri ->
                            val post = generatePost(pr, uri.toString())
                            db.collection(COLLECTION_POST)
                                    .add(post)
                                    .addOnSuccessListener { emitter.onComplete() }
                                    .addOnFailureListener { emitter.onError(it) }
                        }
                    }.addOnFailureListener { emitter.onError(it) }
        }
    }

    override fun updatePost(post: Post): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deletePost(id: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateLike(id: String, users: List<User>): Completable {
        val likeUsers = users.map { it.toMap() }
        return Completable.create { emitter ->
            db.collection(COLLECTION_POST)
                    .document(id)
                    .update(FIELD_LIKE_USERS, likeUsers)
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { emitter.onError(it) }
        }
    }

    private fun generatePost(pr: PostRequest, imageUrl: String) =
            Post(
                    userId = pr.userId,
                    userName = pr.userName,
                    userImage = pr.userImage,
                    image = imageUrl,
                    caption = pr.caption,
                    date = Date())
}
