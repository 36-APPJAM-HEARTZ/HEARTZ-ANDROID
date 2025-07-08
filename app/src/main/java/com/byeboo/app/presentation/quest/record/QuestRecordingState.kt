package com.byeboo.app.presentation.quest.record

import com.byeboo.app.presentation.quest.QuestWritingState

data class QuestRecordingState(
    val step: String = "",
    val stepNumber: Int = 0,
    val questNumber: Int = 0,
    val questTitle: String = "",
    val contents: String = "",
    val contentsState: QuestWritingState = QuestWritingState.BeforeWriting
)