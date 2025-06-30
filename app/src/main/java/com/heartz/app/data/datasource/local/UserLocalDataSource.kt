package com.heartz.app.data.datasource.local

import com.heartz.app.core.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    suspend fun getUserEntity(): UserEntity
    suspend fun isLoggedIn(): Boolean
    fun getNickname(): Flow<String?>
    suspend fun setLoggedIn(loggedIn: Boolean)
    suspend fun saveNickname(nickname: String)
    suspend fun clear()
}
