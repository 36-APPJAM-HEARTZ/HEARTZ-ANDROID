package com.byeboo.app.presentation.quest

sealed class QuestWritingState {
    object BeforeWriting : QuestWritingState()
    object Writing : QuestWritingState()
    object OverLimit : QuestWritingState()
}

data class QuestRecordingState(
    val contents: String = "",
    val contentsState: QuestWritingState = QuestWritingState.BeforeWriting
)
