package com.base.baseapplication.data.repositories

import com.base.baseapplication.data.firebase.FirebaseSource
import io.reactivex.Completable

interface IAuthRepository {
    fun signIn(email: String, password: String): Completable
    fun signUp(email: String, password: String): Completable
    fun logout()
}

class AuthRepositoryImpl constructor(private val firebaseSource: FirebaseSource) : IAuthRepository {
    override fun signIn(email: String, password: String): Completable {
        return firebaseSource.signIn(email, email)
    }

    override fun signUp(email: String, password: String): Completable {
        return firebaseSource.signUp(email, email)
    }

    override fun logout() {
        firebaseSource.logout()
    }
}