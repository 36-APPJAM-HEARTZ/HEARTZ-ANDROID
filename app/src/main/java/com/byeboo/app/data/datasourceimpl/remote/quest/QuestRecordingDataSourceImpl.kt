package com.byeboo.app.data.datasourceimpl.remote.quest

import com.byeboo.app.data.datasource.remote.quest.QuestRecordingDataSource
import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.request.QuestRecordingRequestDto
import com.byeboo.app.data.service.quest.QuestRecordingService
import javax.inject.Inject

class QuestRecordingDataSourceImpl @Inject constructor(
    private val questRecordingService: QuestRecordingService
) : QuestRecordingDataSource {
    override suspend fun postQuestRecording(
        questId: Long,
        request: QuestRecordingRequestDto
    ): BaseResponse<Unit> {
        return questRecordingService.postRecording(questId, request)
    }
}