package com.byeboo.app.domain.model

sealed class QuestWritingState {
    object BeforeWriting : QuestWritingState()
    object Writing : QuestWritingState()
    object OverLimit : QuestWritingState()
}
