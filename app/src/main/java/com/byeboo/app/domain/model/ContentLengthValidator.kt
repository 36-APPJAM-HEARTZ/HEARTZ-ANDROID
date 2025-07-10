package com.byeboo.app.domain.model

sealed class QuestWritingState {
    object BeforeWriting : QuestWritingState()
    object Writing : QuestWritingState()
    object OverLimit : QuestWritingState()
}

object ContentLengthValidator {
    fun validate(text: String, maxLength: Int = 500): QuestWritingState {
        return when {
            text.isBlank() -> QuestWritingState.BeforeWriting
            text.length > maxLength -> QuestWritingState.OverLimit
            else -> QuestWritingState.Writing
        }
    }

    fun validButton(text: String, maxLength: Int = 500): Boolean {
        return text.length >= 10 && text.length <= maxLength
    }
}
