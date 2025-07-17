package com.byeboo.app.data.mapper.quest

import com.byeboo.app.data.dto.request.quest.QuestRecordingRequestDto
import com.byeboo.app.domain.model.quest.QuestRecording

fun QuestRecording.toData(): QuestRecordingRequestDto =
    QuestRecordingRequestDto(
        answer = this.answer,
        questEmotionState = this.questEmotionState
    )
