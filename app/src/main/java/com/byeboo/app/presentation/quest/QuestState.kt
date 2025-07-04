package com.byeboo.app.presentation.quest

sealed class QuestState {
    object InProgress : QuestState()
    object Available : QuestState()
    data class TimerLocked(val remainTime: String) : QuestState()
    object Locked : QuestState()
}
