package com.byeboo.app.domain.usecase

import com.byeboo.app.domain.model.BehaviorAnswerRequestModel
import com.byeboo.app.domain.model.SignedUrlRequestModel
import com.byeboo.app.domain.repository.QuestBehaviorAnswerRepository
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
    private val questBehaviorAnswerRepository: QuestBehaviorAnswerRepository
) {
    suspend operator fun invoke(
        imageBytes: ByteArray,
        contentType: String,
        imageKey: String,
        questId: Long,
        answer: String,
        emotion: String
    ): Result<Unit> = runCatching {
        val signedUrl = questBehaviorAnswerRepository.postQuestSignedUrl(
            SignedUrlRequestModel(contentType, imageKey)
        ).getOrThrow()

        questBehaviorAnswerRepository.putImageToSignedUrl(signedUrl, imageBytes, contentType)

        val request = BehaviorAnswerRequestModel(
            answer = answer,
            questEmotionState = emotion,
            imageKey = imageKey
        )

        questBehaviorAnswerRepository.postQuestBehaviorAnswer(questId, request)
    }
}
