package com.base.baseapplication.data.repositories

import com.base.baseapplication.data.model.UserResponse
import com.base.baseapplication.data.model.UsersResponse
import com.base.baseapplication.data.service.AppApi
import io.reactivex.Observable

interface IUserRepository {
    fun getListUser(page: Int): Observable<UsersResponse>
    fun getUser(userId: Int): Observable<UserResponse>
}

class UserRepositoryImpl constructor(private val appApi: AppApi) : IUserRepository {

    override fun getListUser(page: Int): Observable<UsersResponse> {
        return appApi.getListUser(page)
    }

    override fun getUser(userId: Int): Observable<UserResponse> {
        return appApi.getUser(userId)
    }

}