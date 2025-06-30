package com.byeboo.app.domain.repository

import com.byeboo.app.domain.model.Dummy
import com.byeboo.app.domain.model.DummyResultModel

interface DummyRepository {
    suspend fun getDummies(request: Dummy): Result<DummyResultModel>
}
