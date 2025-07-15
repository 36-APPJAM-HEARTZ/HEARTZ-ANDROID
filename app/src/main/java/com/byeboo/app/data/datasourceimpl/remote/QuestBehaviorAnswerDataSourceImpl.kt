package com.byeboo.app.data.datasourceimpl.remote

import com.byeboo.app.data.datasource.remote.QuestBehaviorAnswerDataSource
import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.base.NullableBaseResponse
import com.byeboo.app.data.dto.request.QuestBehaviorAnswerRequestDto
import com.byeboo.app.data.dto.request.QuestSignedUrlRequestDto
import com.byeboo.app.data.dto.response.QuestBehaviorAnswerResponseDto
import com.byeboo.app.data.dto.response.QuestSingedUrlResponseDto
import com.byeboo.app.data.service.quest.QuestBehaviorService
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class QuestBehaviorAnswerDataSourceImpl @Inject constructor(
    private val questBehaviorService: QuestBehaviorService
): QuestBehaviorAnswerDataSource {

    override suspend fun postQuestSignedUrl(request: QuestSignedUrlRequestDto): BaseResponse<QuestSingedUrlResponseDto> {
        return questBehaviorService.postQuestSignedUrl(request = request)
    }

    override suspend fun putImageToSignedUrl(signedUrl: String, requestBody: RequestBody): Response<Unit> {
        return questBehaviorService.putImageToUrl(signedUrl, requestBody)
    }

    override suspend fun postQuestBehaviorAnswer(
        questId: Long,
        request: QuestBehaviorAnswerRequestDto
    ): NullableBaseResponse<Unit> {
        return questBehaviorService.postQuestAnswer(questId = questId, request = request)
    }

    override suspend fun getQuestBehaviorAnswer(questId: Long): BaseResponse<QuestBehaviorAnswerResponseDto> {
        return questBehaviorService.getQuestBehaviorAnswer(questId = questId)
    }
}