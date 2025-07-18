package com.byeboo.app.data.datasource.remote.quest

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.base.NullableBaseResponse
import com.byeboo.app.data.dto.request.quest.QuestBehaviorAnswerRequestDto
import com.byeboo.app.data.dto.request.quest.QuestSignedUrlRequestDto
import com.byeboo.app.data.dto.response.quest.QuestSingedUrlResponseDto
import okhttp3.RequestBody
import retrofit2.Response

interface QuestBehaviorAnswerDataSource {
    suspend fun postQuestSignedUrl(request: QuestSignedUrlRequestDto): BaseResponse<QuestSingedUrlResponseDto>
    suspend fun putImageToSignedUrl(signedUrl: String, requestBody: RequestBody): Response<Unit>
    suspend fun postQuestBehaviorAnswer(questId: Long, request: QuestBehaviorAnswerRequestDto): NullableBaseResponse<Unit>
}