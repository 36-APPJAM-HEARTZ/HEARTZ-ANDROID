package com.byeboo.app.presentation.quest

import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.core.model.QuestType

data class QuestReviewState(
    val questId: Int = 0,
    val stepNumber: Int = 0,
    val createdAt: String = "2025.06.01.",
    val questQuestion: String = "그 사람이 싫어하기에 내가 포기해야만 했던 일은 무엇일까?",
    val questAnswer: String = "내 X는 질투가 너무 많았다.\n그래서 동아리를 할 수가 없었다...\n특히 솝트처럼 합숙하는 동아린\n완전 금지였다ㅠㅠ",
    val emotion: LargeTagType = LargeTagType.EMOTION_NEUTRAL,
    val emotionDescription: String = "",
    val type: QuestType = QuestType.EMOTION_ORGANIZE
)

sealed interface QuestReviewSideEffect {
    data object NavigateToQuest : QuestReviewSideEffect
}
