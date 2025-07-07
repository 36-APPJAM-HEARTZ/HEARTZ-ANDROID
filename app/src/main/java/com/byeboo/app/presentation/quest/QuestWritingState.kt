package com.byeboo.app.presentation.quest

sealed class QuestWritingState {
    object BeforeWriting : QuestWritingState()
    object Writing : QuestWritingState()
    object OverLimit : QuestWritingState()
}
