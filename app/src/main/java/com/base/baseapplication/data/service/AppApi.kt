package com.base.baseapplication.data.service

import com.base.baseapplication.data.model.LoginResponse
import com.base.baseapplication.data.model.UserResponse
import com.base.baseapplication.data.model.UsersResponse
import io.reactivex.Observable
import retrofit2.http.*

/**
 * REST API access points
 */
interface AppApi {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun signIn(@Body body: HashMap<String, String>): Observable<LoginResponse>

    @GET("users")
    fun getListUser(@Query("page") page: Int): Observable<UsersResponse>

    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Int): Observable<UserResponse>
}
