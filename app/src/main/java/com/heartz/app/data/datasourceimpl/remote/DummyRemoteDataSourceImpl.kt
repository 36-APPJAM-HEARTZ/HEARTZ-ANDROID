package com.heartz.app.data.datasourceimpl.remote

import com.heartz.app.data.datasource.remote.DummyRemoteDataSource
import com.heartz.app.data.dto.base.DummyBaseResponse
import com.heartz.app.data.dto.request.RequestDummyDto
import com.heartz.app.data.dto.response.ResponseDummyDto
import com.heartz.app.data.service.DummyService
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
