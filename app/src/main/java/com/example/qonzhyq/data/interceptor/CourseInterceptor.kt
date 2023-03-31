package com.example.qonzhyq.data.interceptor

import com.example.qonzhyq.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import kotlin.jvm.Throws

class JuzAuthorizationInterceptor(): Interceptor {

    companion object {
        private const val HEADER_AUTH = "Authorization"
        private const val HEADER_PROFILE_TYPE = "x-profile-type"
        private const val HEADER_PROFILE_TYPE_VALUE = "base"
    }

    @Throws(Exception::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder().apply {
            addHeader(HEADER_PROFILE_TYPE, HEADER_PROFILE_TYPE_VALUE)
        }
        val token = Constants.token
        if (token != null) {
            requestBuilder.addHeader(HEADER_AUTH, token)
        }
        return chain.proceed(requestBuilder.build())
    }

}