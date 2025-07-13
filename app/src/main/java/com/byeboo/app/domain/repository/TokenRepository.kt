package com.byeboo.app.domain.repository

interface TokenRepository {
    suspend fun getUserId(): Long?
}
