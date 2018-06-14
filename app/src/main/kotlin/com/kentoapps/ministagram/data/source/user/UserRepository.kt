package com.kentoapps.ministagram.data.source.user

import com.kentoapps.ministagram.data.model.User
import io.reactivex.Completable
import io.reactivex.Observable

interface UserRepository {
    fun signUp(userName: String, email: String, password: String): Completable
    fun signIn(email: String, password: String): Completable
    fun signOut(): Completable
    fun getUser(): Observable<User>
}