package com.byeboo.app.domain.repository.quest

import com.byeboo.app.domain.model.quest.QuestDetailModel

interface QuestDetailBehaviorRepository {
    suspend fun getQuestBehaviorDetail(questId: Long): Result<QuestDetailModel>
}
