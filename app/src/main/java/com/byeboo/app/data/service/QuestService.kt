package com.byeboo.app.data.service

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.base.NullableBaseResponse
import com.byeboo.app.data.dto.response.QuestCountResponseDto
import com.byeboo.app.data.dto.response.QuestDialogueResponseDto
import retrofit2.http.GET
import retrofit2.http.PATCH

interface QuestService {

    @PATCH("/api/v1/users/journey/start")
    suspend fun updateQuestState(): NullableBaseResponse<Unit>

    @GET("/api/v1/users/count")
    suspend fun getQuestCount(): BaseResponse<QuestCountResponseDto>

    @GET("/api/v1/users/character")
    suspend fun getQuestDialogue(): BaseResponse<QuestDialogueResponseDto>
}
