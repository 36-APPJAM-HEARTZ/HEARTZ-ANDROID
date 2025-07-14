package com.byeboo.app.data.repositoryimpl.quest

import com.byeboo.app.data.datasource.local.UserLocalDataSource
import com.byeboo.app.data.datasource.remote.quest.QuestStateDataSource
import com.byeboo.app.data.mapper.toDomain
import com.byeboo.app.domain.model.quest.QuestDialogue
import com.byeboo.app.domain.model.quest.QuestStateModel
import com.byeboo.app.domain.repository.quest.QuestStateRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuestStateRepositoryImpl @Inject constructor(
    private val questStateDataSource: QuestStateDataSource,
    private val userLocalDataSource: UserLocalDataSource
) : QuestStateRepository {
    override suspend fun updateQuestState() {
        runCatching {
            questStateDataSource.updateQuestState()
        }
    }

    override suspend fun updateUserJourney(journey: String) {
        userLocalDataSource.saveJourney(journey)
    }

    override suspend fun getUserJourney(): String? {
        return userLocalDataSource.getJourney()
    }

    override suspend fun getQuestDialogue(): Result<QuestDialogue> {
        return runCatching {
            val response = questStateDataSource.getQuestDialogue()
            response.data.toDomain()
        }
    }

    override suspend fun setQuestStarted(started: Boolean) {
        userLocalDataSource.setQuestStarted(started)
    }

    override suspend fun isQuestStarted(): Boolean {
        return userLocalDataSource.isQuestStarted()
    }

    override fun getQuestCount(): Flow<QuestStateModel> = flow {
        while (true) {
            runCatching {
                questStateDataSource.getQuestCount()
            }.onSuccess { dto ->
                emit(dto.data.toDomain())
            }
            // 추후 바텀시트 버튼 완료시 프로그래스 상태를 업데이트하는 로직으로 변경 예정
            delay(30000L)
        }
    }
}