package com.byeboo.app.domain.model.quest

import com.byeboo.app.domain.model.quest.QuestWritingState

object QuestContentLengthValidator {
    fun validate(isFocused: Boolean, text: String, maxLength: Int = 500): QuestWritingState {
        return when {
            text.isBlank() -> QuestWritingState.Empty
            isFocused -> QuestWritingState.Writing
            text.length > maxLength -> QuestWritingState.OverLimit
            else -> QuestWritingState.Ready
        }
    }

    fun validButton(text: String, maxLength: Int = 500): Boolean {
        return text.length >= 10 && text.length <= maxLength
    }
}