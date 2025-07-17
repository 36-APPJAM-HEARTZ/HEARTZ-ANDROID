package com.byeboo.app.domain.model.quest

object QuestValidator {
    fun validButton(imageCount: Int): Boolean {
        return imageCount > 0
    }
}