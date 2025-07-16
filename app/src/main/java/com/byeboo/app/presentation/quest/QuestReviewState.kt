package com.byeboo.app.presentation.quest

import com.byeboo.app.core.designsystem.type.LargeTagType

data class QuestReviewState(
    val questId: Long = 0,
    val stepNumber: Long = 0,
    val questNumber: Long = 0,
    val createdAt: String = java.time.LocalDate.now().toString(),
    val question: String = "",
    val answer: String = "",
    val imageUrl: String? = null,
    val questEmotionState: String = "",
    val emotionDescription: String = "",
    val selectedEmotion: LargeTagType = LargeTagType.EMOTION_NEUTRAL
)

sealed interface QuestReviewSideEffect {
    data object NavigateToQuest : QuestReviewSideEffect
}
