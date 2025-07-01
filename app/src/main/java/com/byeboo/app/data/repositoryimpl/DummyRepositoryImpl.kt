package com.byeboo.app.data.repositoryimpl

import com.byeboo.app.data.mapper.todata.toData
import com.byeboo.app.data.mapper.todomain.toDomain
import com.byeboo.app.data.service.DummyService
import com.byeboo.app.domain.model.Dummy
import com.byeboo.app.domain.model.DummyResultModel
import com.byeboo.app.domain.repository.DummyRepository
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
