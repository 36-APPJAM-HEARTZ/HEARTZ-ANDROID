package com.byeboo.app.presentation.quest

import androidx.compose.runtime.Immutable
import com.byeboo.app.core.model.QuestType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class QuestTipState(
    val step: QuestType = QuestType.ACTIVE,
    val stepNumber: Long = 0,
    val questNumber: Long = 0,
    val question: String = "",
    val tipStep: ImmutableList<Int> = persistentListOf(1, 2, 3),
    val tipAnswer: ImmutableList<String> = persistentListOf("", "", "")
)

sealed interface QuestTipSideEffect {
    data object NavigateToQuest : QuestTipSideEffect
}