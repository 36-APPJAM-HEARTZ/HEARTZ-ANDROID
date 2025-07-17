package com.byeboo.app.domain.repository

import com.byeboo.app.domain.model.BehaviorAnswerRequestModel
import com.byeboo.app.domain.model.SignedUrlRequestModel

interface QuestBehaviorAnswerRepository {
    suspend fun postQuestSignedUrl(request: SignedUrlRequestModel): Result<String>
    suspend fun putImageToSignedUrl(signUrl: String, imageBytes: ByteArray, contentType: String): Result<Unit>
    suspend fun postQuestBehaviorAnswer(questId: Long, request: BehaviorAnswerRequestModel): Result<Unit>
}
