package com.byeboo.app.domain.model.quest

data class QuestBehaviorAnswerModel(
    val stepNumber: Long,
    val questNumber: Long,
    val createdAt: String,
    val question: String,
    val answer: String,
    val questEmotionState: String,
    val imageUrl: String?,
    val emotionDescription: String
)

