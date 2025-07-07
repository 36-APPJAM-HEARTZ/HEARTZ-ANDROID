package com.byeboo.app.presentation.quest.behavior

data class QuestBehaviorState(
    val stepNumber : Int= 1,
    val stepMissionTitle : String?=null,
    val questNumber: Int=1,
    val questTitle: String="",
    val imageCount: Int = 0
)
