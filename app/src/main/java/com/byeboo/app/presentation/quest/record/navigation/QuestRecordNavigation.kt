package com.byeboo.app.presentation.quest.record.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.util.routeNavigation
import com.byeboo.app.presentation.quest.QuestViewModel
import com.byeboo.app.presentation.quest.record.QuestRecordingCompleteScreen
import com.byeboo.app.presentation.quest.record.QuestRecordingScreen
import com.byeboo.app.presentation.quest.record.QuestRecordingViewModel
import com.byeboo.app.presentation.quest.record.navigation.QuestRecord.QuestRecording
import com.byeboo.app.presentation.quest.record.navigation.QuestRecord.QuestRecordingComplete

fun NavController.navigateToQuestRecording(questId: Int, navOptions: NavOptions? = null) {
    navigate(QuestRecording(questId), navOptions)
}

fun NavController.navigateToQuestRecordingComplete(questId: Int, navOptions: NavOptions? = null) {
    navigate(QuestRecordingComplete(questId), navOptions)
}

fun NavGraphBuilder.questRecordGraph(
    navController: NavController,
    navigateToQuest: () -> Unit,
    navigateToQuestTip: (Int) -> Unit,
    navigateToQuestRecordingComplete: (Int) -> Unit,
    sharedViewModel: QuestViewModel
) {
    routeNavigation<QuestRecord, QuestRecording> {
        composable<QuestRecording> {
            QuestRecordingScreen(
                navController = navController,
                navigateToQuest = navigateToQuest,
                navigateToQuestTip = navigateToQuestTip,
                navigateToQuestRecordingComplete = navigateToQuestRecordingComplete,
                sharedViewModel = sharedViewModel
            )
        }

        composable<QuestRecordingComplete> {
            QuestRecordingCompleteScreen(
                navigateToQuest = navigateToQuest,
                sharedViewModel = sharedViewModel
            )
        }
    }
}