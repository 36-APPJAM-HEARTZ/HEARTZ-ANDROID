package com.byeboo.app.data.datasource.remote.auth

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.request.auth.UserInfoRequestDto
import com.byeboo.app.data.dto.response.auth.UserInfoResponseDto
import com.byeboo.app.data.dto.response.auth.UserJourneyResponseDto

interface UserRemoteDataSource {
    suspend fun updateUserInfo(request: UserInfoRequestDto): BaseResponse<UserInfoResponseDto>
    suspend fun getUserJourney(): BaseResponse<UserJourneyResponseDto>
}