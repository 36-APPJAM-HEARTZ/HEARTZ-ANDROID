package com.byeboo.app.presentation.quest

import com.byeboo.app.core.model.QuestType

sealed class QuestState {
    object Available : QuestState()
    object Complete : QuestState()
    data class TimerLocked(val remainTime: String) : QuestState()
    object Locked : QuestState()
}

sealed interface QuestSideEffect {
    data class NavigateToQuestTip(val questId: Long) : QuestSideEffect
    data class NavigateToQuestRecording(val questId: Long) : QuestSideEffect
    data class NavigateToQuestBehavior(val questId: Long) : QuestSideEffect
    data class NavigateToQuestReview(val questId: Long, val type: QuestType) : QuestSideEffect
    data object NavigateToHome : QuestSideEffect
}
