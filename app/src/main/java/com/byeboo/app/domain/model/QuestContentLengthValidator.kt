package com.byeboo.app.domain.model

sealed class QuestWritingState {
    object BeforeWriting : QuestWritingState()
    object Writing : QuestWritingState()
    object OverLimit : QuestWritingState()
}

object QuestContentLengthValidator {
    fun validate(text: String): QuestWritingState {
        return when {
            text.isBlank() -> QuestWritingState.BeforeWriting
            text.length > 500 -> QuestWritingState.OverLimit
            else -> QuestWritingState.Writing
        }
    }

    fun savable(text: String): Boolean {
        return text.length >= 10 && text.length <= 500
    }

    fun block(text: String): Boolean {
        return text.length <= 500
    }
}