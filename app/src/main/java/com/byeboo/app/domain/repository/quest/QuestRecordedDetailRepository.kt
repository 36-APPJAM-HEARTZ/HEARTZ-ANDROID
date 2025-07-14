package com.byeboo.app.domain.repository.quest

import com.byeboo.app.domain.model.quest.QuestRecordedDetailModel

interface QuestRecordedDetailRepository {
    suspend fun getQuestRecordedDetail(questId: Long): Result<QuestRecordedDetailModel>
}