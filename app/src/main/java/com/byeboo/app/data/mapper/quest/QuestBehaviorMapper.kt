package com.byeboo.app.data.mapper.quest

import com.byeboo.app.data.dto.request.QuestBehaviorAnswerRequestDto
import com.byeboo.app.data.dto.request.QuestSignedUrlRequestDto
import com.byeboo.app.domain.model.BehaviorAnswerRequestModel
import com.byeboo.app.domain.model.SignedUrlRequestModel

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
