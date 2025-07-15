package com.byeboo.app.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestBehaviorAnswerResponseDto(
    @SerialName("stepNumber")
    val stepNumber: Long,
    @SerialName("questNumber")
    val questNumber: Long,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("question")
    val question: String,
    @SerialName("answer")
    val answer: String,
    @SerialName("questionEmotionState")
    val questEmotionState: String,
    @SerialName("imageUrl")
    val imageUrl: String? = null,
    @SerialName("emotionDescription")
    val emotionDescription: String


)