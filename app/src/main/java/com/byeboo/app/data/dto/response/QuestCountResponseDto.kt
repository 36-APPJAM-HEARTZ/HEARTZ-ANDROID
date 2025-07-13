package com.byeboo.app.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestCountResponseDto(
    @SerialName("todayComplete")
    val todayComplete: Boolean,
    @SerialName("count")
    val count: Long
)
