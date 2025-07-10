package com.byeboo.app.presentation.quest.record.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.util.routeNavigation
import com.byeboo.app.presentation.quest.record.QuestRecordingCompleteScreen
import com.byeboo.app.presentation.quest.record.QuestRecordingScreen
import com.byeboo.app.presentation.quest.record.navigation.QuestRecord.QuestRecording
import com.byeboo.app.presentation.quest.record.navigation.QuestRecord.QuestRecordingComplete

fun NavController.navigateToQuestRecord(navOptions: NavOptions? = null) {
    navigate(QuestRecording, navOptions)
}

fun NavGraphBuilder.questRecordGraph(
    navigateToQuest: () -> Unit,
    navigateToQuestTip: () -> Unit
) {
    routeNavigation<QuestRecord, QuestRecording> {
        composable<QuestRecording> {
            QuestRecordingScreen(
                navigateToQuest = navigateToQuest,
                navigateToQuestTip = navigateToQuestTip
            )
        }

        composable<QuestRecordingComplete> {
            QuestRecordingCompleteScreen(
                navigateToQuest = navigateToQuest
            )
        }
    }
}