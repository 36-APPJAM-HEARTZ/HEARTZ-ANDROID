package com.byeboo.app.presentation.quest.record

import androidx.compose.runtime.Immutable
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.core.model.QuestType
import com.byeboo.app.domain.model.QuestWritingState

@Immutable
data class  QuestRecordingState(
    val questId: Long = 0,
    val step: String = "",
    val stepNumber: Long = 0,
    val questNumber: Long = 0,
    val questQuestion: String = "",
    val questAnswer: String = "",
    val contentsState: QuestWritingState = QuestWritingState.BeforeWriting,
    val selectedEmotion: LargeTagType = LargeTagType.EMOTION_NEUTRAL
)

sealed interface QuestRecordingSideEffect {
    data object NavigateToQuest : QuestRecordingSideEffect
    data class NavigateToQuestTip(val questId: Long, val questType: QuestType) : QuestRecordingSideEffect
    data class NavigateToQuestRecordingComplete(val questId: Long) : QuestRecordingSideEffect
}
