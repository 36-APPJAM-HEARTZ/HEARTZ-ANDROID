package com.byeboo.app.data.datasource.remote

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.response.QuestTipResponseDto

interface QuestRemoteDataSource {
    suspend fun getQuestTip(userId: Long, questId: Long): BaseResponse<QuestTipResponseDto>
}