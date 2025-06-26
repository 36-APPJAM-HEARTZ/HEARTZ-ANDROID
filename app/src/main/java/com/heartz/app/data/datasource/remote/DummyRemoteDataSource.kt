package com.heartz.app.data.datasource.remote

import com.heartz.app.data.dto.base.DummyBaseResponse
import com.heartz.app.data.dto.request.RequestDummyDto
import com.heartz.app.data.dto.response.ResponseDummyDto

interface DummyRemoteDataSource {
    suspend fun getDummies(request: RequestDummyDto): DummyBaseResponse<ResponseDummyDto>
}
