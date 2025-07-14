package com.byeboo.app.data.datasource.remote.quest

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.response.QuestDetailResponseDto

interface QuestDetailRemoteDataSource {
    suspend fun getQuestDetail(questId: Long): BaseResponse<QuestDetailResponseDto>
}