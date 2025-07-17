package com.byeboo.app.data.mapper.quest

import com.byeboo.app.data.dto.request.quest.QuestBehaviorAnswerRequestDto
import com.byeboo.app.data.dto.request.quest.QuestSignedUrlRequestDto
import com.byeboo.app.domain.model.quest.BehaviorAnswerRequestModel
import com.byeboo.app.domain.model.quest.SignedUrlRequestModel

fun SignedUrlRequestModel.toData(): QuestSignedUrlRequestDto {
    return QuestSignedUrlRequestDto(
        contentType = this.contentType,
        imageKey = this.imageKey
    )
}

fun BehaviorAnswerRequestModel.toData(): QuestBehaviorAnswerRequestDto {
    return QuestBehaviorAnswerRequestDto(
        answer = this.answer,
        questEmotionState = this.questEmotionState,
        imageKey = imageKey
    )
}
