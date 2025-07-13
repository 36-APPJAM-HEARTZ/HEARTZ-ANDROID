package com.byeboo.app.domain.model

object QuestValidator {
    fun validButton(imageCount: Int): Boolean{
        return imageCount > 0
    }
}