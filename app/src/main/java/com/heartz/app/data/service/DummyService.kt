package com.heartz.app.data.service

import com.heartz.app.data.dto.base.DummyBaseResponse
import com.heartz.app.data.dto.request.RequestDummyDto
import com.heartz.app.data.dto.response.ResponseDummyDto
import retrofit2.http.Body
import retrofit2.http.GET

interface DummyService {
    @GET("/api/v1/service")
    suspend fun getDummies(
        @Body request: RequestDummyDto
    ): DummyBaseResponse<ResponseDummyDto>
}
