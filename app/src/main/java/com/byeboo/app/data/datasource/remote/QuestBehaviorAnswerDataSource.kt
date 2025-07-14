package com.byeboo.app.data.datasource.remote

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.base.NullableBaseResponse
import com.byeboo.app.data.dto.request.QuestBehaviorAnswerRequestDto
import com.byeboo.app.data.dto.request.QuestSignedUrlRequestDto
import com.byeboo.app.data.dto.response.QuestSingedUrlResponseDto

interface QuestBehaviorAnswerDataSource {
    suspend fun postQuestSignedUrl(request: QuestSignedUrlRequestDto) : BaseResponse<QuestSingedUrlResponseDto>
    suspend fun postQuestBehaviorAnswer(questId: Long, request: QuestBehaviorAnswerRequestDto) : NullableBaseResponse<Unit>
}