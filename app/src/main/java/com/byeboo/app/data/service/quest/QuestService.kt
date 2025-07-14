package com.byeboo.app.data.service

import com.byeboo.app.data.dto.base.BaseResponse
import com.byeboo.app.data.dto.base.NullableBaseResponse
import com.byeboo.app.data.dto.response.QuestCountResponseDto
import com.byeboo.app.data.dto.response.QuestDialogueResponseDto
import com.byeboo.app.data.dto.response.QuestInProgressResponseDto
import retrofit2.http.GET
import retrofit2.http.PATCH

interface QuestService {
    //최초 여정 상태 변경
    @PATCH("/api/v1/users/journey/start")
    suspend fun updateQuestState(): NullableBaseResponse<Unit>

    //퀘스트 진행도
    @GET("/api/v1/users/count")
    suspend fun getQuestCount(): BaseResponse<QuestCountResponseDto>

    //퀘스트 상태에 따른 캐릭터 대사
    @GET("/api/v1/users/character")
    suspend fun getQuestDialogue(): BaseResponse<QuestDialogueResponseDto>

    //진행중인 전체 퀘스트 조회
    @GET("/api/v1/quests/all/progress")
    suspend fun getInProgressQuest(): BaseResponse<QuestInProgressResponseDto>
}