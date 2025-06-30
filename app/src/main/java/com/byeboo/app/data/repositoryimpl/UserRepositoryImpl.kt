package com.byeboo.app.data.repositoryimpl

import com.byeboo.app.core.model.UserEntity
import com.byeboo.app.data.datasource.local.UserLocalDataSource
import com.byeboo.app.domain.repository.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : UserRepository {
    override suspend fun getUserEntity(): UserEntity {
        return userLocalDataSource.getUserEntity()
    }

    override suspend fun isLoggedIn(): Boolean {
        return userLocalDataSource.isLoggedIn()
    }

    override fun getNickname(): Flow<String?> {
        return userLocalDataSource.getNickname()
    }

    override suspend fun setLoggedIn(loggedIn: Boolean) {
        userLocalDataSource.setLoggedIn(loggedIn)
    }

    override suspend fun saveNickname(nickname: String) {
        userLocalDataSource.saveNickname(nickname)
    }

    override suspend fun clear() {
        userLocalDataSource.clear()
    }
}
