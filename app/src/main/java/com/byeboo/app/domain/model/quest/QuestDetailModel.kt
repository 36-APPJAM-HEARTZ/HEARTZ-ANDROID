package com.byeboo.app.domain.model.quest

import com.byeboo.app.domain.model.QuestStyle

data class QuestDetailModel(
    val step: String,
    val stepNumber: Long,
    val questNumber: Long,
    val questStyle: QuestStyle,
    val question: String
)
