package com.byeboo.app.data.datasourceimpl.remote

import com.byeboo.app.data.datasource.remote.UserRemoteDataSource
import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.request.UserInfoRequestDto
import com.byeboo.app.data.dto.response.UserInfoResponseDto
import com.byeboo.app.data.dto.response.UserJourneyResponseDto
import com.byeboo.app.data.service.auth.UserService
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService
) : UserRemoteDataSource {
    override suspend fun updateUserInfo(request: UserInfoRequestDto): BaseResponse<UserInfoResponseDto> {
        return userService.updateUserInfo(request)
    }

    override suspend fun getUserJourney(): BaseResponse<UserJourneyResponseDto> {
        return userService.getJourney()
    }
}
