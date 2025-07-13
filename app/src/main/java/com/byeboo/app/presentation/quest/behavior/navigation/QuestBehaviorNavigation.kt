package com.byeboo.app.presentation.quest.behavior.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.byeboo.app.core.util.routeNavigation
import com.byeboo.app.presentation.quest.behavior.QuestBehaviorCompleteScreen
import com.byeboo.app.presentation.quest.behavior.QuestBehaviorWritingScreen
import com.byeboo.app.presentation.quest.behavior.navigation.QuestBehavior.QuestBehaviorComplete
import com.byeboo.app.presentation.quest.behavior.navigation.QuestBehavior.QuestBehaviorWriting
import com.byeboo.app.presentation.quest.record.navigation.QuestRecord

fun NavController.navigateToQuestBehavior(questId: Long, navOptions: NavOptions? = null) {
    navigate(QuestBehaviorWriting(questId), navOptions)
}

fun NavController.navigateToQuestBehaviorComplete(questId: Long, navOptions: NavOptions? = null) {
    navigate(QuestBehaviorComplete(questId), navOptions)
}

fun NavGraphBuilder.questBehaviorGraph(
    navigateToQuest: () -> Unit,
    navigateToQuestTip: (Long) -> Unit,
    navigateToQuestBehaviorComplete: (Long) -> Unit
) {
    routeNavigation<QuestBehavior, QuestBehaviorWriting> {
        composable<QuestBehaviorWriting> { backStackEntry ->
            val questRecording = backStackEntry.toRoute<QuestRecord.QuestRecording>()
            val questId = questRecording.questId

            QuestBehaviorWritingScreen(
                questId = questId,
                navigateToQuest = navigateToQuest,
                navigateToQuestTip = navigateToQuestTip,
                navigateToQuestBehaviorComplete = navigateToQuestBehaviorComplete
            )
        }

        composable<QuestBehaviorComplete> { backStackEntry ->
            val questRecording = backStackEntry.toRoute<QuestRecord.QuestRecording>()
            val questId = questRecording.questId

            QuestBehaviorCompleteScreen(
                questId = questId,
                navigateToQuest = navigateToQuest
            )
        }
    }
}
