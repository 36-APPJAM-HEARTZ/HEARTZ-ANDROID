package com.byeboo.app.data.repositoryimpl.quest

import com.byeboo.app.data.datasource.remote.quest.QuestRecordedDetailDataSource
import com.byeboo.app.data.mapper.quest.toDomain
import com.byeboo.app.domain.model.quest.QuestRecordedDetailModel
import com.byeboo.app.domain.repository.quest.QuestRecordedDetailRepository
import javax.inject.Inject

class QuestRecordedDetailRepositoryImpl @Inject constructor(
    private val questRecordedDetailDataSource: QuestRecordedDetailDataSource
) : QuestRecordedDetailRepository {
    override suspend fun getQuestRecordedDetail(questId: Long): Result<QuestRecordedDetailModel> = runCatching {
        val response = questRecordedDetailDataSource.getQuestRecordedDetail(questId)
        response.data.toDomain()
    }
}
