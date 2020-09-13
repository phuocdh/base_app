package com.base.baseapplication.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by PhuocDH on 9/12/2020.
 */

data class UserResponse(
    @SerializedName("data")
    val user: User
)