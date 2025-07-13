package com.byeboo.app.domain.model

data class QuestDetailModel(
    val step: String,
    val stepNumber: Long,
    val questNumber: Long,
    val questStyle: QuestStyle,
    val question: String
)


