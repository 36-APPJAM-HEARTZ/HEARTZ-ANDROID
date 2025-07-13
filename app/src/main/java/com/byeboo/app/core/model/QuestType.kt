package com.byeboo.app.core.model

import com.byeboo.app.R

enum class QuestType(val questName: String, val imageRes: Int) {
    EMOTION_FACE("감정 직면", R.drawable.img_emotion_face),
    EMOTION_ORGANIZE("감정 정리", R.drawable.img_emotion_organize);

    companion object {
        fun from(journeyName: String): QuestType {
            return entries.find { it.questName == journeyName }
                ?: EMOTION_FACE
        }
    }
}
