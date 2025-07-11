package com.byeboo.app.presentation.quest.model

import com.byeboo.app.core.model.QuestType
import com.byeboo.app.presentation.quest.QuestState

data class QuestGroup(
    val questNumber: Int,
    val stepTitle: String,
    val quests: List<Quest>
)

data class Quest(
    val questId: Int = 0,
    val questNumber: Int = 0,
    val questQuestion: String = "연애에서 반복됐던 문제 패턴 3가지를 생각해보아요.",
    val state: QuestState = QuestState.Available,
    val type: QuestType = QuestType.EMOTION_ORGANIZE
)
