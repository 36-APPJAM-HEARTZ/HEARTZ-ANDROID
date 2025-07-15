package com.byeboo.app.domain.model

data class SignedUrlRequestModel(
    val contentType : String,
    val imageKey: String
)

data class BehaviorAnswerRequestModel(
    val answer: String,
    val questEmotionState: String,
    val imageKey: String
)