package com.kentoapps.ministagram.data.source.user

import com.kentoapps.ministagram.data.model.User
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val dataSource: UserDataSource) : UserRepository {
    override fun signUp(userName: String, email: String, password: String): Completable {
        return dataSource.signUp(userName, email, password)
    }

    override fun signIn(email: String, password: String): Completable {
        return dataSource.signIn(email, password)
    }

    override fun signOut(): Completable {
        return dataSource.signOut()
    }

    override fun getUser(): Observable<User> {
        return dataSource.getUser()
    }
}