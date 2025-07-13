package com.byeboo.app.domain.model

data class QuestDetailModel(
    val step: String,
    val stepNumber: Int,
    val questNumber: Int,
    val questStyle: QuestStyle,
    val question: String
)


