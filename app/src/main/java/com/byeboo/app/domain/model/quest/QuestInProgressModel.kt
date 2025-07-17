package com.byeboo.app.domain.model.quest

data class QuestInProgressModel(
    val progressPeriod: Long,
    val currentStep: Int,
    val steps: List<QuestStepModel>
)

data class QuestStepModel(
    val stepNumber: Long,
    val stepTitle: String,
    val quests: List<QuestItemModel>
)

data class QuestItemModel(
    val questId: Long,
    val question: String,
    val questStyle: String,
    val questNumber: Long
)
