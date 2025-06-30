package com.byeboo.app.data.datasourceimpl.remote

import com.byeboo.app.data.datasource.remote.DummyRemoteDataSource
import com.byeboo.app.data.dto.base.DummyBaseResponse
import com.byeboo.app.data.dto.request.RequestDummyDto
import com.byeboo.app.data.dto.response.ResponseDummyDto
import com.byeboo.app.data.service.DummyService
import javax.inject.Inject

class DummyRemoteDataSourceImpl
@Inject
constructor(
    private val dummyService: DummyService
) : DummyRemoteDataSource {
    override suspend fun getDummies(
        request: RequestDummyDto
    ): DummyBaseResponse<ResponseDummyDto> =
        dummyService.getDummies(request)
}
