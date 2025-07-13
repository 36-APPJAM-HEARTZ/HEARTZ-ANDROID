package com.byeboo.app.data.mapper.todomain

import com.byeboo.app.data.dto.response.QuestDetailResponseDto
import com.byeboo.app.domain.model.QuestDetailModel
import com.byeboo.app.domain.model.QuestStyle

fun QuestDetailResponseDto.toDomain() : QuestDetailModel {
    return QuestDetailModel(
        step = this.step,
        stepNumber = this.stepNumber,
        questNumber = this.questNumber,
        questStyle = QuestStyle.valueOf(this.questStyle),
        question = this.question
    )
}