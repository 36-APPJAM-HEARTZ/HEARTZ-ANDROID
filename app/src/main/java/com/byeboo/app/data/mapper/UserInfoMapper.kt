package com.byeboo.app.data.mapper

import com.byeboo.app.core.model.UserEntity
import com.byeboo.app.data.dto.request.UserInfoRequestDto
import com.byeboo.app.data.dto.response.UserInfoResponseDto
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
fun UserInfoResponseDto.toDomain(): UserEntity = UserEntity(
    userId = this.id,
    nickname = this.name
)

fun UserJourneyResponseDto.toDomain(): UserJourney {
    return UserJourney(
        journey = this.journey,
        description = this.description
    )
}
