package com.byeboo.app.data.datasourceimpl.remote

import com.byeboo.app.data.datasource.remote.QuestRemoteDataSource
import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.response.QuestTipResponseDto
import com.byeboo.app.data.service.quest.QuestService
import javax.inject.Inject

class QuestRemoteDataSourceImpl @Inject constructor(
    private val questService: QuestService
) : QuestRemoteDataSource {
    override suspend fun getQuestTip(
        userId: Long,
        questId: Long
    ): BaseResponse<QuestTipResponseDto> {
        return questService.getQuestTip(userId, questId)
    }
}