package com.byeboo.app.data.datasource.remote

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.base.NullableBaseResponse
import com.byeboo.app.data.dto.request.QuestBehaviorAnswerRequestDto
import com.byeboo.app.data.dto.request.QuestSignedUrlRequestDto
import com.byeboo.app.data.dto.response.QuestBehaviorAnswerResponseDto
import com.byeboo.app.data.dto.response.QuestSingedUrlResponseDto
import okhttp3.RequestBody
import retrofit2.Response

interface QuestBehaviorAnswerDataSource {
    suspend fun postQuestSignedUrl(request: QuestSignedUrlRequestDto) : BaseResponse<QuestSingedUrlResponseDto>
    suspend fun putImageToSignedUrl(signedUrl: String, requestBody : RequestBody) : Response<Unit>
    suspend fun postQuestBehaviorAnswer(questId: Long, request: QuestBehaviorAnswerRequestDto) : NullableBaseResponse<Unit>
    suspend fun getQuestBehaviorAnswer(questId: Long): BaseResponse<QuestBehaviorAnswerResponseDto>
}