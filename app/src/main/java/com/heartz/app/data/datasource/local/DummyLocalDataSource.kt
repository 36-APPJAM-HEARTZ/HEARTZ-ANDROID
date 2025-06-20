package org.sopt.dateroad.data.datalocal.datasourceimpl

import kotlinx.coroutines.flow.Flow

// TODO: 임시
interface DummyLocalDataSource {
    val isLogin: Flow<Boolean>

    suspend fun setIsLogin(value: Boolean)

    suspend fun clear()
}
