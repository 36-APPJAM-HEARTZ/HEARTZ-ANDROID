package com.byeboo.app.presentation.quest

sealed class QuestState {
    object InProgress : QuestState()
    object Available : QuestState()
    data class TimerLocked(val remainTime: String) : QuestState()
    object Locked : QuestState()
}

sealed interface QuestSideEffect {
    data object NavigateToHome : QuestSideEffect
    data object NavigateToMypage : QuestSideEffect
}
