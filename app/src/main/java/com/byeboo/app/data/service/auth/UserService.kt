package com.byeboo.app.data.service.auth

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.request.UserInfoRequestDto
import com.byeboo.app.data.dto.response.UserInfoResponseDto
import com.byeboo.app.data.dto.response.UserJourneyResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST("/api/v1/users")
    suspend fun updateUserInfo(
        @Body request: UserInfoRequestDto
    ): BaseResponse<UserInfoResponseDto>

    @GET("/api/v1/users/journey")
    suspend fun getJourney(
    ): BaseResponse<UserJourneyResponseDto>
}