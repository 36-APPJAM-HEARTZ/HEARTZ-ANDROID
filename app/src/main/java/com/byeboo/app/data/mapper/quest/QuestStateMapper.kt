package com.byeboo.app.data.mapper.quest

import com.byeboo.app.data.dto.response.quest.QuestCountResponseDto
import com.byeboo.app.data.dto.response.quest.QuestDialogueResponseDto
import com.byeboo.app.domain.model.quest.QuestDialogue
import com.byeboo.app.domain.model.quest.QuestStateModel

fun QuestCountResponseDto.toDomain(): QuestStateModel {
    return QuestStateModel(
        todayComplete = this.todayComplete,
        count = this.count.toInt()
    )
}

fun QuestDialogueResponseDto.toDomain(): QuestDialogue {
    return QuestDialogue(
        dialogue = this.dialogue
    )
}
