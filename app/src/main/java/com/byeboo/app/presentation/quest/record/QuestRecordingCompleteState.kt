package com.byeboo.app.presentation.quest.record

import androidx.compose.runtime.Immutable
import com.byeboo.app.core.designsystem.type.LargeTagType

@Immutable
data class QuestRecordingCompleteState(
    val questId: Long = 0,
    val stepNumber: Long = 0,
    val questNumber: Long = 0,
    val createdAt: String = java.time.LocalDate.now().toString(),
    val questQuestion: String = "연애에서 반복됐던 문제 패턴 3가지를 생각해보아요.",
    val questAnswer: String = "내 X는 질투가 너무 많았다. 그래서 동아리를 할 수가 없었다… 특히 솝트처럼 합숙하는 동아린 완전 금지였다ㅜㅜ",
    val questEmotionState: String = "",
    val emotionDescription: String = "",
    val selectedEmotion: LargeTagType = LargeTagType.EMOTION_NEUTRAL
)

sealed interface QuestRecordingCompleteSideEffect {
    data object NavigateToQuest : QuestRecordingCompleteSideEffect
}
