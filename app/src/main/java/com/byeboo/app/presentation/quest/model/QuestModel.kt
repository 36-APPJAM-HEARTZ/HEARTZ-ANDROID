package com.byeboo.app.presentation.quest.model

import com.byeboo.app.core.model.quest.QuestType

data class QuestGroup(
    val questNumber: Long,
    val stepTitle: String,
    val quests: List<Quest>
)

data class Quest(
    val questId: Long = 0,
    val questNumber: Long = 0,
    val questQuestion: String = "연애에서 반복됐던 문제 패턴 3가지를 생각해보아요.",
    val state: QuestState = QuestState.Available,
    val type: QuestType = QuestType.RECORDING
)
