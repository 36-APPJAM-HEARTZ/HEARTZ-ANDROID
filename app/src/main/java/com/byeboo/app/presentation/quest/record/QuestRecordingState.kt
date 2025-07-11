package com.byeboo.app.presentation.quest.record

import androidx.compose.runtime.Immutable
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.domain.model.QuestWritingState

@Immutable
data class QuestRecordingState(
    val questId: Int = 0,
    val step: String = "감정 쏟아내기",
    val stepNumber: Int = 0,
    val questNumber: Int = 0,
    val questQuestion: String = "연애에서 반복됐던 문제 패턴 3가지를 생각해보아요.",
    val contents: String = "",
    val contentsState: QuestWritingState = QuestWritingState.BeforeWriting,
    val selectedEmotion: LargeTagType = LargeTagType.EMOTION_NEUTRAL
)

sealed interface QuestRecordingSideEffect{
    data object NavigateToQuest: QuestRecordingSideEffect
    data class NavigateToQuestTip(val questId: Int): QuestRecordingSideEffect
    data class NavigateToQuestRecordingComplete(val questId: Int): QuestRecordingSideEffect
}