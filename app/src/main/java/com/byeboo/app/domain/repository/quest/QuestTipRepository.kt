package com.byeboo.app.domain.repository.quest

import com.byeboo.app.domain.model.quest.QuestTip

interface QuestTipRepository {
    suspend fun getQuestTip(questId: Long): Result<QuestTip>
}