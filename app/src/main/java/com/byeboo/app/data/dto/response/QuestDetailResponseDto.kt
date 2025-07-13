package com.byeboo.app.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestDetailResponseDto(
    @SerialName("step")
    val step: String,
    @SerialName("stepNumber")
    val stepNumber: Int,
    @SerialName("questNumber")
    val questNumber: Int,
    @SerialName("questStyle")
    val questStyle: String,
    @SerialName("question")
    val question: String
)