package com.byeboo.app.domain.repository.quest

import com.byeboo.app.domain.model.quest.QuestRecording

interface QuestRecordingRepository {
    suspend fun postRecording(questId: Long, request: QuestRecording): Result<Unit>
}
