package com.base.baseapplication.data.repositories

import com.base.baseapplication.data.sharedprf.SharedPrefsApi
import com.base.baseapplication.data.sharedprf.SharedPrefsKey
import com.base.baseapplication.utils.extension.notNull

interface ITokenRepository {
    fun setAuthToken(token: String?)
    fun getAuthToken(): String?
    fun clearToken()
}

class TokenRepositoryImpl constructor(private val sharedPrefsApi: SharedPrefsApi) : ITokenRepository {

    override fun setAuthToken(token: String?) {
        sharedPrefsApi.put(SharedPrefsKey.PREF_AUTH_TOKEN, token)
    }

    override fun getAuthToken(): String? {
        val str = sharedPrefsApi.get(SharedPrefsKey.PREF_AUTH_TOKEN, String::class)
        str.notNull {
            return it
        }
        return ""
    }

    override fun clearToken() {
        sharedPrefsApi.clear()
    }
}