package com.byeboo.app.data.repositoryimpl.auth

import com.byeboo.app.data.datasource.local.UserLocalDataSource
import com.byeboo.app.domain.repository.auth.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : TokenRepository {

    override suspend fun getUserId(): Long? {
        return userLocalDataSource.getUserId()
    }
}
