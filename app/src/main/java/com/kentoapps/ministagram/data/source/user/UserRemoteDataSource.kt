package com.kentoapps.ministagram.data.source.user

import com.kentoapps.ministagram.data.model.User
import io.reactivex.Completable
import io.reactivex.Observable

class UserRemoteDataSource : UserDataSource {
    override fun signUp(userName: String, email: String, password: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signIn(email: String, password: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signOut(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUser(): Observable<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}