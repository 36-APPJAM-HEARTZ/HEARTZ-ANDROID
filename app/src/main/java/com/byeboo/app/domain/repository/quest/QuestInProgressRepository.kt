package com.byeboo.app.domain.repository.quest

interface QuestInProgressRepository {
    suspend fun getInProgressQuest(): Result<QuestInProgressModel>
}