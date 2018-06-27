package com.kentoapps.ministagram.data.source.user

import com.google.firebase.auth.FirebaseAuth
import com.kentoapps.ministagram.data.model.User
import com.kentoapps.ministagram.util.extension.createUser
import com.kentoapps.ministagram.util.extension.signIn
import io.reactivex.Completable
import io.reactivex.Observable

class UserRemoteDataSource : UserDataSource {
    override fun signUp(userName: String, email: String, password: String): Completable {
        return Completable.create { emitter ->
            FirebaseAuth.getInstance()
                    .createUser(email, password) { task ->
                        if (task.isSuccessful) emitter.onComplete()
                        else emitter.onError(Throwable(task.exception?.message))
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
        return Observable.just(User("123", "kento_user", "https://avatars1.githubusercontent.com/u/8979200?s=460&v=4"))
    }
}