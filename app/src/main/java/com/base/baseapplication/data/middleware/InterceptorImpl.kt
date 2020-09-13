package com.base.baseapplication.data.middleware

import androidx.annotation.NonNull
import com.base.baseapplication.utils.LogUtils
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class InterceptorImpl() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {
        val builder = initializeHeader(chain)
        val request = builder.build()
        return chain.proceed(request)
    }

    private fun initializeHeader(chain: Interceptor.Chain): Request.Builder {
        val originRequest = chain.request()
        val newUrl = chain.request().url.toString()
        LogUtils.e("newUrl", newUrl)
        val builder = originRequest.newBuilder()
            .url(newUrl)
            .method(originRequest.method, originRequest.body)
        builder.addHeader("Content-Type", "application/json")
        return builder
    }
}