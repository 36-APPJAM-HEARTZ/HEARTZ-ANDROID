package com.heartz.app.data.repositoryimpl

import com.heartz.app.data.mapper.todata.toData
import com.heartz.app.data.mapper.todomain.toDomain
import com.heartz.app.data.service.DummyService
import com.heartz.app.domain.model.Dummy
import com.heartz.app.domain.model.DummyResultModel
import com.heartz.app.domain.repository.DummyRepository
import javax.inject.Inject

class DummyRepositoryImpl
@Inject
constructor(
    private val dummyService: DummyService
) : DummyRepository {
    override suspend fun getDummies(request: Dummy): Result<DummyResultModel> =
        runCatching {
            val response = dummyService.getDummies(request = request.toData())
            response.data.toDomain()
        }
}
