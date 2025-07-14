package com.byeboo.app.data.mapper

import com.byeboo.app.data.dto.request.UserInfoRequestDto
import com.byeboo.app.data.dto.response.UserJourneyResponseDto
import com.byeboo.app.domain.model.UserInfoModel
import com.byeboo.app.domain.model.UserJourney

fun UserInfoModel.toData(): UserInfoRequestDto {
    return UserInfoRequestDto(
        name = this.name,
        feeling = this.feeling,
        questStyle = this.questStyle
    )
}

fun UserJourneyResponseDto.toDomain(): UserJourney {
    return UserJourney(
        journey = this.journey,
        description = this.description
    )
}