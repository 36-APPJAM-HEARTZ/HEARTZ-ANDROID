package com.byeboo.app.core.model

import com.byeboo.app.R

enum class QuestType(
    val questName: String,
    val frontImg: Int,
    val backImg: Int
) {
    RECORDING(
        "RECORDING",
        R.drawable.img_recording_amulet_front,
        R.drawable.img_recording_amulet_back
    ),
    ACTIVE(
        "ACTIVE",
        R.drawable.img_active_amulet_front,
        R.drawable.img_active_amulet_back
    );

    companion object {
        fun from(journeyName: String): QuestType {
            return entries.find { it.questName == journeyName }
                ?: RECORDING
        }
    }
}
