package com.byeboo.app.domain.repository

import com.byeboo.app.data.dto.request.QuestBehaviorAnswerRequestDto
import com.byeboo.app.data.dto.request.QuestSignedUrlRequestDto

interface QuestBehaviorAnswerRepository {
    suspend fun postQuestSignedUrl(request: QuestSignedUrlRequestDto): String

    suspend fun postQuestBehaviorAnswer(questId: Long, request: QuestBehaviorAnswerRequestDto)
}


