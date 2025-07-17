package com.byeboo.app.data.datasource.remote.quest

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.response.QuestRecordedDetailResponseDto

interface QuestRecordedDetailDataSource {
    suspend fun getQuestRecordedDetail(questId: Long): BaseResponse<QuestRecordedDetailResponseDto>
}
