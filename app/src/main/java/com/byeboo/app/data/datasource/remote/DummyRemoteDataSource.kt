package com.byeboo.app.data.datasource.remote

import com.byeboo.app.data.dto.base.DummyBaseResponse
import com.byeboo.app.data.dto.request.RequestDummyDto
import com.byeboo.app.data.dto.response.ResponseDummyDto

interface DummyRemoteDataSource {
    suspend fun getDummies(request: RequestDummyDto): DummyBaseResponse<ResponseDummyDto>
}
