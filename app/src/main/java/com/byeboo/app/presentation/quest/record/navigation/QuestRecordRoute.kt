package com.byeboo.app.presentation.quest.record.navigation

import com.byeboo.app.core.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
sealed class QuestRecord : Route {
    @Serializable
    data class QuestRecording(val questId: Long) : QuestRecord()

    @Serializable
    data class QuestRecordingComplete(val questId: Long) : QuestRecord()
}
