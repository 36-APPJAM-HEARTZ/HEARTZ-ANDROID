package com.byeboo.app.data.mapper.quest

import com.byeboo.app.data.dto.response.quest.QuestInProgressResponseDto
import com.byeboo.app.domain.model.quest.QuestInProgressModel
import com.byeboo.app.domain.model.quest.QuestItemModel
import com.byeboo.app.domain.model.quest.QuestStepModel

fun QuestInProgressResponseDto.toDomain(): QuestInProgressModel {
    return QuestInProgressModel(
        progressPeriod = progressPeriod,
        currentStep = currentStep,
        steps = steps.map { stepDto ->
            QuestStepModel(
                stepNumber = stepDto.stepNumber,
                stepTitle = stepDto.step,
                quests = stepDto.quests.map { questDto ->
                    QuestItemModel(
                        questId = questDto.questId,
                        question = questDto.question,
                        questStyle = questDto.questStyle,
                        questNumber = questDto.questNumber
                    )
                }
            )
        }
    )
}
