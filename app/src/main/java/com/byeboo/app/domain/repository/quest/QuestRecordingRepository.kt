package com.byeboo.app.domain.repository.quest

import com.byeboo.app.data.dto.base.NullableBaseResponse
import com.byeboo.app.domain.model.quest.QuestRecording

interface QuestRecordingRepository {
    suspend fun postRecording(questId: Long, request: QuestRecording): NullableBaseResponse<Unit>
}