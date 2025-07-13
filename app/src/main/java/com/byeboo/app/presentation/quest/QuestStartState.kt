package com.byeboo.app.presentation.quest

import androidx.compose.runtime.Immutable

@Immutable
data class QuestStartState(
    val nickname: String = "",
    val journeyName: String = ""
)

sealed interface QuestStartSideEffect {
    data object NavigateToQuest : QuestStartSideEffect
    data object NavigateToHome : QuestStartSideEffect
}
