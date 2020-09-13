package com.base.baseapplication.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by PhuocDH on 9/13/2020.
 */
data class User(
    @SerializedName("id")
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("avatar")
    val avatar: String,
)