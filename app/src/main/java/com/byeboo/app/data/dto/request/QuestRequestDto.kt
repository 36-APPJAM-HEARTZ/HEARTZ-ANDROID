package com.byeboo.app.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestRequestDto(
    @SerialName("questId")
    val questId: Long
)
