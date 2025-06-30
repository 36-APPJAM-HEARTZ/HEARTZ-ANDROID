package com.byeboo.app.domain.repository

import com.byeboo.app.core.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getUserEntity(): UserEntity
    suspend fun isLoggedIn(): Boolean
    fun getNickname(): Flow<String?>
    suspend fun setLoggedIn(loggedIn: Boolean)
    suspend fun saveNickname(nickname: String)
    suspend fun clear()
}
