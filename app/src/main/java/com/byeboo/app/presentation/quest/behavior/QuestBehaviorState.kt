package com.byeboo.app.presentation.quest.behavior

import com.byeboo.app.presentation.quest.QuestWritingState

data class QuestBehaviorState(
    val stepNumber : Int= 1,
    val stepMissionTitle : String?=null,
    val questNumber: Int=1,
    val questTitle: String="",
    val imageCount: Int = 0,
    val contents: String="",
    val contentState: QuestWritingState = QuestWritingState.BeforeWriting
)
