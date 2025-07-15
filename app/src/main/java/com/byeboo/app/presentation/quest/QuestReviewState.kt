package com.byeboo.app.presentation.quest

import com.byeboo.app.core.designsystem.type.LargeTagType

data class QuestReviewState(
    val questId: Long = 0,
    val stepNumber: Long = 0,
    val questNumber: Long = 0,
    val createdAt: String = java.time.LocalDate.now().toString(),
    val question: String = "그 사람이 싫어하기에 내가 포기해야만 했던 일은 무엇일까?",
    val answer: String = "내 X는 질투가 너무 많았다.\n그래서 동아리를 할 수가 없었다...\n특히 솝트처럼 합숙하는 동아린\n완전 금지였다ㅠㅠ",
    val imageUrl: String? = null,
    val questEmotionState: String = "",
    val emotionDescription: String = "",
    val selectedEmotion: LargeTagType = LargeTagType.EMOTION_NEUTRAL
)

sealed interface QuestReviewSideEffect {
    data object NavigateToQuest : QuestReviewSideEffect
}
