package com.byeboo.app.data.datasource.local

import kotlinx.coroutines.flow.Flow

interface DummyLocalDataSource {
    val isLogin: Flow<Boolean>

    suspend fun setIsLogin(value: Boolean)

    suspend fun clear()
}
