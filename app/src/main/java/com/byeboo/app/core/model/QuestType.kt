package com.byeboo.app.core.model

import com.byeboo.app.R

enum class QuestType(val questName: String, val imageRes: Int) {
    RECORDING("RECORDING", R.drawable.img_emotion_face),
    ACTIVE("ACTIVE", R.drawable.img_emotion_organize);

    companion object {
        fun from(journeyName: String): QuestType {
            return entries.find { it.questName == journeyName }
                ?: RECORDING
        }
    }
}