package com.byeboo.app.data.datasourceimpl.remote

import com.byeboo.app.data.datasource.remote.QuestInProgressDataSource
import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.response.QuestInProgressResponseDto
import com.byeboo.app.data.service.QuestService
import javax.inject.Inject

class QuestInProgressDataSourceImpl @Inject constructor(
    private val questService: QuestService
) : QuestInProgressDataSource {
    override suspend fun getInProgressQuest(): BaseResponse<QuestInProgressResponseDto> {
        return questService.getInProgressQuest()
    }
}