package com.byeboo.app.presentation.quest.record

import com.byeboo.app.domain.model.QuestWritingState

data class QuestRecordingState(
    val step: String = "감정 쏟아내기",
    val stepNumber: Int = 0,
    val questNumber: Int = 0,
    val questTitle: String = "연애에서 반복됐던 문제 패턴 3가지를 생각해보아요.",
    val contents: String = "",
    val contentsState: QuestWritingState = QuestWritingState.BeforeWriting
)