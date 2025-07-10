package com.byeboo.app.presentation.quest.model

import com.byeboo.app.presentation.quest.QuestState

data class QuestGroup(
    val stepTitle: String,
    val quests: List<Quest>
)

data class Quest(
    val questId: Int,
    val questNumber: Int,
    val state: QuestState
)
