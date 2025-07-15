package com.byeboo.app.presentation.quest

import com.byeboo.app.presentation.quest.model.Quest
import com.byeboo.app.presentation.quest.model.QuestGroup

data class QuestUiState(
    val quest: Quest = Quest(),
    val questGroups: List<QuestGroup> = emptyList(),
    val progressPeriod: Long = 1L,
    val journeyTitle: String = "",
    val currentStepIndex: Int = 0,
    val userName: String = "",
    val selectedQuest: Quest? = null,
    val showQuitModal: Boolean = false,
    val isLoading: Boolean = false
)