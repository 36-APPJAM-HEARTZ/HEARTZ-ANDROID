package com.byeboo.app.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestDialogueResponseDto(
    @SerialName("dialogue")
    val dialogue: String
)
