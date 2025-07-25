package com.byeboo.app.presentation.quest.model

import com.byeboo.app.core.model.quest.QuestType

sealed class QuestState {
    object Available : QuestState()
    object Complete : QuestState()
    data class TimerLocked(val remainTime: String) : QuestState()
    object Locked : QuestState()
}

sealed interface QuestSideEffect {
    data class NavigateToQuestTip(val questId: Long, val questType: QuestType) : QuestSideEffect
    data class NavigateToQuestRecording(val questId: Long) : QuestSideEffect
    data class NavigateToQuestBehavior(val questId: Long) : QuestSideEffect
    data class NavigateToQuestReview(val questId: Long) : QuestSideEffect
    data object NavigateToHome : QuestSideEffect
}
