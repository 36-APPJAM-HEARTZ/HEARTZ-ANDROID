package com.byeboo.app.presentation.quest.behavior

import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.domain.model.QuestWritingState

data class QuestBehaviorState(
    val stepNumber: Int = 1,
    val stepMissionTitle: String? = null,
    val questId: Int = 1,
    val questNumber: Int = 1,
    val questTitle: String = "",
    val imageCount: Int = 0,
    val createdAt: String = "",
    val contents: String = "",
    val isContentAvailable: Boolean = false,
    val contentState: QuestWritingState = QuestWritingState.BeforeWriting,
    val selectedEmotion: LargeTagType = LargeTagType.EMOTION_NEUTRAL
)

sealed interface QuestBehaviorSideEffect {
    data object NavigateToQuest : QuestBehaviorSideEffect
    data class NavigateToQuestTip(val questId: Int) : QuestBehaviorSideEffect
    data class NavigateToQuestBehaviorComplete(val questID: Int) : QuestBehaviorSideEffect
}
