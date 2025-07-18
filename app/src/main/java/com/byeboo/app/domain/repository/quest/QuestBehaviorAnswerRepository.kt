package com.byeboo.app.domain.repository.quest

import com.byeboo.app.domain.model.quest.BehaviorAnswerRequestModel
import com.byeboo.app.domain.model.quest.SignedUrlRequestModel

interface QuestBehaviorAnswerRepository {
    suspend fun postQuestSignedUrl(request: SignedUrlRequestModel): Result<String>
    suspend fun putImageToSignedUrl(signUrl: String, imageBytes: ByteArray, contentType: String): Result<Unit>
    suspend fun postQuestBehaviorAnswer(questId: Long, request: BehaviorAnswerRequestModel): Result<Unit>
}
