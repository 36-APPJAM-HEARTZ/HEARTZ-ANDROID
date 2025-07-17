package com.byeboo.app.data.datasource.remote

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.response.QuestInProgressResponseDto

interface QuestInProgressDataSource {
    suspend fun getInProgressQuest(): BaseResponse<QuestInProgressResponseDto>
}
