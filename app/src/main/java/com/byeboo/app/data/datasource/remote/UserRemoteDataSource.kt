package com.byeboo.app.data.datasource.remote

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.request.UserInfoRequestDto
import com.byeboo.app.data.dto.response.UserInfoResponseDto
import com.byeboo.app.data.dto.response.UserJourneyResponseDto

interface UserRemoteDataSource {
    suspend fun updateUserInfo(request: UserInfoRequestDto): BaseResponse<UserInfoResponseDto>
    suspend fun getUserJourney(): BaseResponse<UserJourneyResponseDto>
}
