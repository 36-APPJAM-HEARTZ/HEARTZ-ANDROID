package com.byeboo.app.domain.repository.auth

interface TokenRepository {
    suspend fun getUserId(): Long?
}
