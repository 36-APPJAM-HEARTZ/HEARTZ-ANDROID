package com.byeboo.app.presentation.quest

sealed class QuestState {
    object Available : QuestState()
    object Complete: QuestState()
    data class TimerLocked(val remainTime: String) : QuestState()
    object Locked : QuestState()
}

sealed interface QuestSideEffect {
    data class NavigateToQuestTip(val questId: Int) : QuestSideEffect
    data class NavigateToQuestRecording(val questId: Int) : QuestSideEffect
    data class NavigateToQuestBehavior(val questId: Int) : QuestSideEffect
}
