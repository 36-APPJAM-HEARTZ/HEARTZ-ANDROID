package com.byeboo.app.domain.repository.quest

import com.byeboo.app.domain.model.quest.QuestDialogue
import com.byeboo.app.domain.model.quest.QuestStateModel
import com.byeboo.app.domain.model.quest.QuestTip
import kotlinx.coroutines.flow.Flow

interface QuestStateRepository {
    suspend fun updateQuestState()
    suspend fun updateUserJourney(journey: String)
    suspend fun getUserJourney(): String?
    suspend fun getQuestDialogue(): Result<QuestDialogue>
    suspend fun isQuestStarted(): Boolean
    suspend fun setQuestStarted(started: Boolean)
    fun getQuestCount(): Flow<QuestStateModel>
}