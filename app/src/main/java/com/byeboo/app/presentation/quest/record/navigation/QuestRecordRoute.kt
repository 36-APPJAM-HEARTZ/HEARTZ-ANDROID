package com.byeboo.app.presentation.quest.record.navigation

import com.byeboo.app.core.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
sealed class QuestRecord : Route {
    @Serializable
    data object QuestRecording : QuestRecord()

    @Serializable
    data object QuestRecordingComplete : QuestRecord()
}

