package com.heartz.app.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDummyDto(
    @SerialName("info")
    val info: List<String>,
)
