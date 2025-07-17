package com.byeboo.app.core.network

import com.byeboo.app.domain.repository.auth.TokenRepository
import javax.inject.Inject
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val userId = runBlocking { tokenRepository.getUserId() }

        val newRequest = request.newBuilder()
            .addHeader(USER_ID_HEADER_KEY, userId?.toString() ?: "")
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        const val USER_ID_HEADER_KEY = "userId"
    }
}
