package com.alpha.galleryphoto.domain.repository.common.remote.retrofit

import com.alpha.galleryphoto.domain.repository.user.local.IUserLocalStorage
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor(private val localStorage: IUserLocalStorage) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type", "application/json; charset=UTF-8")
            .addHeader("Authorization", getToken())
            .build()
        return chain.proceed(request)
    }

    private fun getToken(): String {
        val currentUser = localStorage.getCurrentUser()

        return (currentUser?.token) ?: ""
    }
}