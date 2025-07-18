package com.byeboo.app.data.repositoryimpl.quest

import com.byeboo.app.data.datasource.remote.quest.QuestBehaviorAnswerDataSource
import com.byeboo.app.data.mapper.quest.toData
import com.byeboo.app.domain.model.quest.BehaviorAnswerRequestModel
import com.byeboo.app.domain.model.quest.SignedUrlRequestModel
import com.byeboo.app.domain.repository.quest.QuestBehaviorAnswerRepository
import javax.inject.Inject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class QuestBehaviorAnswerRepositoryImpl @Inject constructor(
    private val questBehaviorAnswerDataSource: QuestBehaviorAnswerDataSource
) : QuestBehaviorAnswerRepository {

    override suspend fun postQuestSignedUrl(request: SignedUrlRequestModel): Result<String> {
        return runCatching {
            val response = questBehaviorAnswerDataSource.postQuestSignedUrl(request.toData())
            response.data.signedUrl
        }
    }

    override suspend fun putImageToSignedUrl(
        signUrl: String,
        imageBytes: ByteArray,
        contentType: String
    ): Result<Unit> {
        return runCatching {
            val body = imageBytes.toRequestBody(contentType.toMediaTypeOrNull())
            questBehaviorAnswerDataSource.putImageToSignedUrl(signUrl, body)
        }
    }

    override suspend fun postQuestBehaviorAnswer(questId: Long, request: BehaviorAnswerRequestModel): Result<Unit> {
        return runCatching {
            questBehaviorAnswerDataSource.postQuestBehaviorAnswer(questId, request.toData())
        }
    }
}
