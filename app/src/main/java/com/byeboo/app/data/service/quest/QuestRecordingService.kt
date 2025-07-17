package com.byeboo.app.data.service.quest

import com.byeboo.app.data.dto.base.NullableBaseResponse
import com.byeboo.app.data.dto.request.QuestRecordingRequestDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface QuestRecordingService {
    @POST("/api/v1/quests/{questId}/recording")
    suspend fun postRecording(
        @Path("questId") questId: Long,
        @Body request: QuestRecordingRequestDto
    ): NullableBaseResponse<Unit>
}
