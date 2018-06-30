package com.kentoapps.ministagram.data.source.user

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.kentoapps.ministagram.data.model.User
import com.kentoapps.ministagram.util.COLLECTION_USER
import com.kentoapps.ministagram.util.extension.createUser
import com.kentoapps.ministagram.util.extension.signIn
import io.reactivex.Completable
import io.reactivex.Observable

class UserRemoteDataSource : UserDataSource {
    override fun isSignIn(): Observable<Boolean> =
            Observable.just(FirebaseAuth.getInstance().currentUser != null)

    override fun signUp(userName: String, email: String, password: String): Completable {
        return Completable.create { emitter ->
            FirebaseAuth.getInstance()
                    .createUser(email, password) { task ->
                        if (task.isSuccessful) {
                            FirebaseFirestore.getInstance().collection(COLLECTION_USER)
                                    .document(task.result.user.uid)
                                    .set(User(userId = task.result.user.uid, userName = userName))
                                    .addOnSuccessListener { emitter.onComplete() }
                                    .addOnFailureListener { emitter.onError(it) }
                        } else emitter.onError(Throwable(task.exception?.message))

                    }
        }
    }

    override fun signIn(email: String, password: String): Completable {
        return Completable.create { emitter ->
            FirebaseAuth.getInstance().signIn(email, password) { task ->
                if (task.isSuccessful) emitter.onComplete()
                else emitter.onError(Throwable(task.exception?.message))
            }
        }
    }

    override fun signOut(): Completable {
        return Completable.create { emitter ->
            FirebaseAuth.getInstance().signOut()
            emitter.onComplete()
        }
    }

    override fun getUser(): Observable<User> {
        return Observable.create { emitter ->
            val user = FirebaseAuth.getInstance().currentUser
            if (user == null) {
                emitter.onError(Throwable("You're not signed in"))
            } else {
                FirebaseFirestore.getInstance().collection(COLLECTION_USER)
                        .document(user.uid)
                        .get()
                        .addOnSuccessListener { snapshot ->
                            val result = snapshot.toObject(User::class.java)
                            if (result != null) emitter.onNext(result)
                            else emitter.onError(Throwable("Failed to get user"))
                        }
            }
        }
    }
}