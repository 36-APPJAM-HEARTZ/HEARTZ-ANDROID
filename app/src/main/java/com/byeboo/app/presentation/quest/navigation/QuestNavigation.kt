package com.byeboo.app.presentation.quest.navigation

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.byeboo.app.core.util.routeNavigation
import com.byeboo.app.presentation.quest.QuestScreen
import com.byeboo.app.presentation.quest.QuestStartScreen
import com.byeboo.app.presentation.quest.QuestTipScreen
import com.byeboo.app.presentation.quest.QuestViewModel
import com.byeboo.app.presentation.quest.behavior.navigation.questBehaviorGraph
import com.byeboo.app.presentation.quest.record.navigation.questRecordGraph

fun NavController.navigateToQuestStart(navOptions: NavOptions? = null) {
    navigate(QuestStart, navOptions)
}

fun NavController.navigateToQuest(navOptions: NavOptions? = null) {
    navigate(Quest, navOptions)
}

fun NavController.navigateToQuestTip(questId: Int, navOptions: NavOptions? = null) {
    navigate(QuestTip(questId), navOptions)
}

fun NavGraphBuilder.questGraph(
    questStartBackButton: () -> Unit,
    navigateToQuest: () -> Unit,
    navigateToQuestRecording: (Int) -> Unit,
    navigateToQuestBehavior: (Int) -> Unit,
    navigateToQuestRecordingComplete: (Int) -> Unit,
    navigateToQuestTip: (Int) -> Unit,
    navigateToQuestBehaviorComplete: (Int) -> Unit,
    bottomPadding: Dp
) {
    routeNavigation<Quest, QuestStart> {
        composable<QuestStart> {
            QuestStartScreen(
                navigateBack = questStartBackButton,
                navigateQuest = navigateToQuest
            )
        }

        composable<Quest> {
            QuestScreen(
                navigateToQuestTip = navigateToQuestTip,
                navigateToQuestRecording = navigateToQuestRecording,
                navigateToQuestBehavior = navigateToQuestBehavior,
                bottomPadding = bottomPadding
            )
        }

        composable<QuestTip> { backStackEntry ->
            val questTip = backStackEntry.toRoute<QuestTip>()
            val questId = questTip.questId

            QuestTipScreen(
                questId = questId,
                navigateBack = navigateToQuestRecording
            )
        }

        questRecordGraph(
            navigateToQuest = navigateToQuest,
            navigateToQuestTip = navigateToQuestTip,
            navigateToQuestRecordingComplete = navigateToQuestRecordingComplete,
        )

        questBehaviorGraph(
            navigateToQuest = navigateToQuest,
            navigateToQuestTip = navigateToQuestTip,
            navigateToQuestBehaviorComplete = navigateToQuestBehaviorComplete,
        )
    }
}
