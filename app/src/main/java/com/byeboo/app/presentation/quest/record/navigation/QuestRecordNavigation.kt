package com.byeboo.app.presentation.quest.record.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.byeboo.app.core.util.routeNavigation
import com.byeboo.app.presentation.quest.record.QuestRecordingCompleteScreen
import com.byeboo.app.presentation.quest.record.QuestRecordingScreen
import com.byeboo.app.presentation.quest.record.navigation.QuestRecord.QuestRecording
import com.byeboo.app.presentation.quest.record.navigation.QuestRecord.QuestRecordingComplete

fun NavController.navigateToQuestRecording(questId: Int, navOptions: NavOptions? = null) {
    navigate(QuestRecording(questId), navOptions)
}

fun NavController.navigateToQuestRecordingComplete(questId: Int, navOptions: NavOptions? = null) {
    navigate(QuestRecordingComplete(questId), navOptions)
}

fun NavGraphBuilder.questRecordGraph(
    navigateToQuest: () -> Unit,
    navigateToQuestTip: (Int) -> Unit,
    navigateToQuestRecordingComplete: (Int) -> Unit
) {
    routeNavigation<QuestRecord, QuestRecording> {
        composable<QuestRecording> { backStackEntry ->
            val questRecording = backStackEntry.toRoute<QuestRecording>()
            val questId = questRecording.questId

            QuestRecordingScreen(
                questId = questId,
                navigateToQuest = navigateToQuest,
                navigateToQuestTip = navigateToQuestTip,
                navigateToQuestRecordingComplete = navigateToQuestRecordingComplete
            )
        }

        composable<QuestRecordingComplete> { backStackEntry ->
            val questRecordingComplete = backStackEntry.toRoute<QuestRecordingComplete>()
            val questId = questRecordingComplete.questId

            QuestRecordingCompleteScreen(
                questId = questId,
                navigateToQuest = navigateToQuest
            )
        }
    }
}
