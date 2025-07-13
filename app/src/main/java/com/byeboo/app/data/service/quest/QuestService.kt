package com.byeboo.app.data.service.quest

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.response.QuestTipResponseDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface QuestService {
    @GET("/api/v1/quests/{questId}/tip")
    suspend fun getQuestTip(
        @Header("userId") userId: Long,
        @Path("questId") questId: Long
    ): BaseResponse<QuestTipResponseDto>
}