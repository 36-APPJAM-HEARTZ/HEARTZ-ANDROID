package com.byeboo.app.domain.model

object QuestContentLengthValidator {
    fun validate(isFocused: Boolean, text: String, maxLength: Int = 500): QuestWritingState {
        return if (isFocused) {
            if (text.isBlank()) {
                QuestWritingState.BeforeWriting
            } else if (text.length > maxLength) {
                QuestWritingState.OverLimit
            } else {
                QuestWritingState.Writing
            }
        } else {
            QuestWritingState.Done
        }
    }

    fun validButton(text: String, maxLength: Int = 500): Boolean {
        return text.length >= 10 && text.length <= maxLength
    }
}
