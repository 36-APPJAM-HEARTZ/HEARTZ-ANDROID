package com.byeboo.app.data.datasource.remote.quest

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.response.QuestTipResponseDto

interface QuestTipDataSource {
    suspend fun getQuestTip(questId: Long): BaseResponse<QuestTipResponseDto>
}