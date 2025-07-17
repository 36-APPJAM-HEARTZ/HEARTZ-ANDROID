package com.byeboo.app.presentation.quest.behavior

import android.net.Uri
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.core.model.QuestType
import com.byeboo.app.domain.model.QuestWritingState

data class QuestBehaviorState(
    val stepNumber: Long = 1,
    val stepMissionTitle: String? = null,
    val questId: Long = 1,
    val questNumber: Long = 1,
    val question: String = "",
    val imageCount: Int = 0,
    val createdAt: String = "",
    var contents: String = "",
    val answer: String = "",
    val imageUrl: String = "",
    val questEmotionState: String = "",
    val emotionDescription: String = "",
    val isContentAvailable: Boolean = false,
    val contentState: QuestWritingState = QuestWritingState.BeforeWriting,
    val selectedEmotion: LargeTagType = LargeTagType.EMOTION_NEUTRAL,
    val showBottomSheet: Boolean = false,
    val isEmotionSelected: Boolean = false,
    val selectedImageUri: Uri? = null,
    val showQuitModal: Boolean = false,
    val isUploading: Boolean = false
)

sealed interface QuestBehaviorSideEffect {
    data object NavigateToQuest : QuestBehaviorSideEffect
    data class NavigateToQuestTip(val questId: Long, val questType: QuestType) : QuestBehaviorSideEffect
    data class NavigateToQuestBehaviorComplete(val questId: Long) : QuestBehaviorSideEffect
    data class CompleteAndClear(val questId: Long) : QuestBehaviorSideEffect
}
