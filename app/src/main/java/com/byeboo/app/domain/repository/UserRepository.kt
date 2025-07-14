package com.byeboo.app.domain.repository

import com.byeboo.app.core.model.UserEntity
import com.byeboo.app.domain.model.UserInfoModel
import com.byeboo.app.domain.model.UserJourney
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun updateUserInfo(userInfo: UserInfoModel): Result<UserEntity>
    suspend fun getUserEntity(): UserEntity
    suspend fun getUserJourney(): Result<UserJourney>
    suspend fun isLoggedIn(): Boolean
    fun getNickname(): Flow<String?>
    suspend fun getUserId(): Long?
    suspend fun clear()
}
