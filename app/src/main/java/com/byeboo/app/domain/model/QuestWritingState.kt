package com.byeboo.app.domain.model

sealed class QuestWritingState {
    object Empty : QuestWritingState()
    object Writing : QuestWritingState()
    object OverLimit : QuestWritingState()
    object Ready: QuestWritingState()
}
