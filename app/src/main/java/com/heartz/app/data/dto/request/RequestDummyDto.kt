package com.heartz.app.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestDummyDto(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String
)
