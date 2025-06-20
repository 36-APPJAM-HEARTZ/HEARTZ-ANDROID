package com.heartz.app.domain.repository

import com.heartz.app.domain.model.Dummy
import com.heartz.app.domain.model.DummyResultModel

interface DummyRepository {
    suspend fun getDummies(request: Dummy): Result<DummyResultModel>
}
