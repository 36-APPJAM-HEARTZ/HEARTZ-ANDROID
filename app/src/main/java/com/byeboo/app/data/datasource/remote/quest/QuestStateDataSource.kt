package com.byeboo.app.data.datasource.remote.quest

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.base.NullableBaseResponse
import com.byeboo.app.data.dto.response.QuestCountResponseDto
import com.byeboo.app.data.dto.response.QuestDialogueResponseDto

interface QuestStateDataSource {
    suspend fun updateQuestState(): NullableBaseResponse<Unit>
    suspend fun getQuestCount(): BaseResponse<QuestCountResponseDto>
    suspend fun getQuestDialogue(): BaseResponse<QuestDialogueResponseDto>
}