package com.base.baseapplication.data.sharedprf

import kotlin.reflect.KClass

interface SharedPrefsApi {
    fun <T : Any> get(key: String, clazz: KClass<T>): T

    fun <T> put(key: String, data: T)

    fun clear()

    fun clearKey(key: String)
}