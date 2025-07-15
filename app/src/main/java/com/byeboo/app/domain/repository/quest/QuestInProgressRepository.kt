package com.byeboo.app.domain.repository.quest

import com.byeboo.app.domain.model.quest.QuestInProgressModel

interface QuestInProgressRepository {
    suspend fun getInProgressQuest(): Result<QuestInProgressModel>
}