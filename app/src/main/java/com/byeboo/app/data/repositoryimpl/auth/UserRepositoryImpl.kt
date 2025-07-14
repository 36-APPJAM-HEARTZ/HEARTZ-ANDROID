package com.byeboo.app.data.repositoryimpl.auth

import com.byeboo.app.core.model.UserEntity
import com.byeboo.app.data.datasource.local.UserLocalDataSource
import com.byeboo.app.data.datasource.remote.UserRemoteDataSource
import com.byeboo.app.data.mapper.toData
import com.byeboo.app.data.mapper.toDomain
import com.byeboo.app.domain.model.UserInfoModel
import com.byeboo.app.domain.model.UserJourney
import com.byeboo.app.domain.repository.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun updateUserInfo(userInfo: UserInfoModel): Result<UserEntity> {
        return runCatching {
            val response = userRemoteDataSource.updateUserInfo(userInfo.toData())
            val userEntity = UserEntity(userId = response.data!!.id, nickname = response.data.name)
            userLocalDataSource.saveId(userEntity.userId!!)
            userLocalDataSource.saveNickname(userEntity.nickname!!)
            userLocalDataSource.setLoggedIn(true)
            userEntity
        }
    }
    override suspend fun getUserJourney(): Result<UserJourney> {
        return runCatching {
            val response = userRemoteDataSource.getUserJourney()
            response.data!!.toDomain()
        }
    }
    override suspend fun getUserEntity(): UserEntity {
        return userLocalDataSource.getUserEntity()
    }

    override suspend fun isLoggedIn(): Boolean {
        return userLocalDataSource.isLoggedIn()
    }

    override fun getNickname(): Flow<String?> {
        return userLocalDataSource.getNickname()
    }

    override suspend fun getUserId(): Long? {
        return userLocalDataSource.getUserId()
    }

    override suspend fun clear() {
        userLocalDataSource.clear()
    }
}
