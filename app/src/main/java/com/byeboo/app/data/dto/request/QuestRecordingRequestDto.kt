package com.byeboo.app.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestRecordingRequestDto(
    @SerialName("answer")
    val answer: String,
    @SerialName("questEmotionState")
    val questEmotionState: String
)
