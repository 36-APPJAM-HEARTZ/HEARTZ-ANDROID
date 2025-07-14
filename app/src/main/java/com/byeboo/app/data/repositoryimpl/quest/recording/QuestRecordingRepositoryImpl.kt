package com.byeboo.app.data.repositoryimpl.quest.recording

import com.byeboo.app.data.datasource.remote.quest.QuestRecordingDataSource
import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.mapper.quest.toData
import com.byeboo.app.domain.model.quest.QuestRecording
import com.byeboo.app.domain.repository.quest.QuestRecordingRepository
import javax.inject.Inject

class QuestRecordingRepositoryImpl @Inject constructor(
    private val questRecordingDataSource: QuestRecordingDataSource
) : QuestRecordingRepository {
    override suspend fun postRecording(
        questId: Long,
        request: QuestRecording
    ): BaseResponse<Unit> {
        return questRecordingDataSource.postQuestRecording(questId, request.toData())
    }
}