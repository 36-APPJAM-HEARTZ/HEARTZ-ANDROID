package com.byeboo.app.domain.repository.quest

import com.byeboo.app.core.model.QuestDetailEntity
import com.byeboo.app.domain.model.QuestDetailModel

interface QuestDetailRecordingRepository {
    suspend fun getQuestRecordingDetail(questId: Long) : Result<QuestDetailModel>
}