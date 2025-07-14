package com.byeboo.app.data.repositoryimpl

import com.byeboo.app.data.datasource.remote.QuestBehaviorAnswerDataSource
import com.byeboo.app.data.mapper.toData
import com.byeboo.app.domain.model.BehaviorAnswerRequestModel
import com.byeboo.app.domain.model.SignedUrlRequestModel
import com.byeboo.app.domain.repository.QuestBehaviorAnswerRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class QuestBehaviorAnswerRepositoryImpl @Inject constructor(
    private val questBehaviorAnswerDataSource: QuestBehaviorAnswerDataSource
) : QuestBehaviorAnswerRepository {

    override suspend fun postQuestSignedUrl(request: SignedUrlRequestModel): Result<String> {
        return runCatching {
            val response = questBehaviorAnswerDataSource.postQuestSignedUrl(request= request.toData())
            response.data.signedUrl
        }
    }

    override suspend fun putImageToSignedUrl(
        signedUrl: String,
        imageBytes: ByteArray,
        contentType: String
    ): Result<Unit> {
        return runCatching {
            val request = imageBytes.toRequestBody(contentType.toMediaTypeOrNull())
            questBehaviorAnswerDataSource.putImageToSignedUrl(signedUrl, request)
        }
    }

    override suspend fun postQuestBehaviorAnswer(
        questId: Long,
        request: BehaviorAnswerRequestModel
    ): Result<Unit> {
        return runCatching {
            questBehaviorAnswerDataSource.postQuestBehaviorAnswer(questId, request = request.toData())
        }
    }
}