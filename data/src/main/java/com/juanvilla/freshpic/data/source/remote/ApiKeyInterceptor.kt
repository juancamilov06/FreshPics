package com.juanvilla.freshpic.data.source.remote

import com.juanvilla.freshpic.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        val request = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(request)
    }
}