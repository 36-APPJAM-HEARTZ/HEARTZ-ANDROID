package com.byeboo.app.data.mapper.quest

import com.byeboo.app.data.dto.response.QuestTipResponseDto
import com.byeboo.app.data.dto.response.Tip
import com.byeboo.app.domain.model.quest.QuestTip
import com.byeboo.app.domain.model.quest.QuestTips

fun QuestTipResponseDto.toDomain(): QuestTip {
    return QuestTip(
        question = this.question,
        step = this.step,
        stepNumber = this.stepNumber,
        questNumber = this.questNumber,
        tips = this.tips.map { it: Tip -> it.toDomain() }
    )
}

fun Tip.toDomain(): QuestTips {
    return QuestTips(
        tipStep = this.tipStep,
        tipAnswer = this.tipAnswer
    )
}