package com.base.baseapplication.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
class LoginResponse(
    @SerializedName("token")
    var token: String,
)