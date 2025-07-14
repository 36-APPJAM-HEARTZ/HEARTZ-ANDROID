package com.byeboo.app.data.repositoryimpl

import androidx.compose.ui.autofill.ContentType
import com.byeboo.app.data.datasource.remote.QuestBehaviorAnswerDataSource
import com.byeboo.app.data.dto.request.QuestBehaviorAnswerRequestDto
import com.byeboo.app.data.dto.request.QuestSignedUrlRequestDto
import com.byeboo.app.domain.repository.QuestBehaviorAnswerRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import javax.inject.Inject

class QuestBehaviorAnswerRepositoryImpl @Inject constructor(
    private val questBehaviorAnswerDataSource: QuestBehaviorAnswerDataSource
) : QuestBehaviorAnswerRepository{

    override suspend fun postQuestSignedUrl(request: QuestSignedUrlRequestDto): String {
        return runCatching {
            val response = questBehaviorAnswerDataSource.postQuestSignedUrl(request)
            response.data.signedUrl
        }.getOrThrow()
    }

    override suspend fun putImageToSignedUrl(
        signUrl: String,
        imageBytes: ByteArray,
        contentType: String
    ) {
        val requestBody = imageBytes.toRequestBody(contentType.toMediaTypeOrNull())
        val response = questBehaviorAnswerDataSource.putImageToSignedUrl(signUrl, requestBody)
        if (!response.isSuccessful) {
            throw IOException("Image upload failed with code ${response.code()}")
        }
    }

    override suspend fun postQuestBehaviorAnswer(
        questId: Long,
        request: QuestBehaviorAnswerRequestDto
    ) {
        TODO("Not yet implemented")
    }
}