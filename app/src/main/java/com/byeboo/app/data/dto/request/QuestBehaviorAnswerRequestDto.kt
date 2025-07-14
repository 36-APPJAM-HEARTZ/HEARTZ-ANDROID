package com.byeboo.app.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class QuestBehaviorAnswerRequestDto(
    @SerialName("answer")
    val answer: String? = null,
    @SerialName("questionEmotionState")
    val questionEmotionState: String,
    @SerialName("imageKey")
    val imageKey: String
)

