package com.byeboo.app.domain.repository.quest

import com.byeboo.app.domain.model.quest.QuestDetailModel

interface QuestDetailRecordingRepository {
    suspend fun getQuestRecordingDetail(questId: Long) : Result<QuestDetailModel>
}