package com.byeboo.app.data.datasourceimpl.remote

import com.byeboo.app.data.datasource.remote.quest.QuestTipDataSource
import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.response.QuestTipResponseDto
import com.byeboo.app.data.service.quest.QuestTipService
import javax.inject.Inject

class QuestTipDataSourceImpl @Inject constructor(
    private val questTipService: QuestTipService
) : QuestTipDataSource {
    override suspend fun getQuestTip(
        questId: Long
    ): BaseResponse<QuestTipResponseDto> {
        return questTipService.getQuestTip(questId)
    }
}