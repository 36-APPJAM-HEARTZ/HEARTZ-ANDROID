package com.byeboo.app.data.repositoryimpl

import com.byeboo.app.data.datasource.remote.QuestInProgressDataSource
import com.byeboo.app.data.mapper.toDomain
import com.byeboo.app.domain.model.quest.QuestInProgressModel
import com.byeboo.app.domain.repository.QuestInProgressRepository
import javax.inject.Inject

class QuestInProgressRepositoryImpl @Inject constructor(
    private val questInProgressDataSource: QuestInProgressDataSource
) : QuestInProgressRepository {

    override suspend fun getInProgressQuest(): Result<QuestInProgressModel> {
        return runCatching {
            val response = questInProgressDataSource.getInProgressQuest()
            response.data.toDomain()
        }
    }
}
