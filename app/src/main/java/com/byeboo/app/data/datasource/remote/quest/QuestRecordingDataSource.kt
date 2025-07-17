package com.byeboo.app.data.datasource.remote.quest

import com.byeboo.app.data.dto.base.NullableBaseResponse
import com.byeboo.app.data.dto.request.QuestRecordingRequestDto

interface QuestRecordingDataSource {
    suspend fun postQuestRecording(questId: Long, request: QuestRecordingRequestDto): NullableBaseResponse<Unit>
}
