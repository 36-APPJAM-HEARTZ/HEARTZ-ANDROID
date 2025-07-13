package com.byeboo.app.presentation.quest

import com.byeboo.app.core.model.QuestType

sealed class QuestState {
    object Available : QuestState()
    object Complete : QuestState()
    data class TimerLocked(val remainTime: String) : QuestState()
    object Locked : QuestState()
}

sealed interface QuestSideEffect {
    data class NavigateToQuestTip(val questId: Int) : QuestSideEffect
    data class NavigateToQuestRecording(val questId: Int) : QuestSideEffect
    data class NavigateToQuestBehavior(val questId: Int) : QuestSideEffect
    data class NavigateToQuestReview(val questId: Int, val questType: QuestType) : QuestSideEffect
}
