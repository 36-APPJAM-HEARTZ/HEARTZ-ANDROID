package com.byeboo.app.data.service

import com.byeboo.app.data.dto.base.DummyBaseResponse
import com.byeboo.app.data.dto.request.RequestDummyDto
import com.byeboo.app.data.dto.response.ResponseDummyDto
import retrofit2.http.Body
import retrofit2.http.POST

interface DummyService {
    // TODO: 이름은 getDummies지만 @Body를 사용해버려서 post로 바꿨습니다,, 임시!
    @POST("/api/v1/service")
    suspend fun getDummies(
        @Body request: RequestDummyDto
    ): DummyBaseResponse<ResponseDummyDto>
}
