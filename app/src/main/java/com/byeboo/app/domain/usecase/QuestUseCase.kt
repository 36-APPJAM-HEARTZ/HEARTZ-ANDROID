package com.byeboo.app.domain.usecase

import com.byeboo.app.domain.model.quest.QuestData
import com.byeboo.app.domain.repository.QuestInProgressRepository
import com.byeboo.app.domain.repository.UserRepository
import com.byeboo.app.domain.repository.quest.QuestStateRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class QuestUseCase @Inject constructor(
    private val questInProgressRepository: QuestInProgressRepository,
    private val questStateRepository: QuestStateRepository,
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): QuestData {
        val result = questInProgressRepository.getInProgressQuest().getOrThrow()
        val journey = questStateRepository.getUserJourney() ?: ""
        val nickname = userRepository.getNickname().firstOrNull() ?: ""
        return QuestData(
            inProgressQuest = result,
            journeyTitle = journey,
            userNickname = nickname
        )
    }
}