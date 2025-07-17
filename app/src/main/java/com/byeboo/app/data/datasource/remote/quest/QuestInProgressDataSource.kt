package com.byeboo.app.data.datasource.remote.quest

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.response.quest.QuestInProgressResponseDto

interface QuestInProgressDataSource {
    suspend fun getInProgressQuest(): BaseResponse<QuestInProgressResponseDto>
}