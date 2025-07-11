package com.byeboo.app.presentation.quest.navigation

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.util.routeNavigation
import com.byeboo.app.presentation.quest.QuestScreen
import com.byeboo.app.presentation.quest.QuestStartScreen
import com.byeboo.app.presentation.quest.QuestViewModel
import com.byeboo.app.presentation.quest.behavior.navigation.questBehaviorGraph
import com.byeboo.app.presentation.quest.record.navigation.navigateToQuestRecordingComplete
import com.byeboo.app.presentation.quest.record.navigation.questRecordGraph

fun NavController.navigateToQuestStart(navOptions: NavOptions? = null) {
    navigate(QuestStart, navOptions)
}

fun NavController.navigateToQuest(navOptions: NavOptions? = null) {
    navigate(Quest, navOptions)
}

fun NavGraphBuilder.questGraph(
    navController: NavController,
    questStartBackButton: () -> Unit,
    navigateToQuest: () -> Unit,
    navigateToQuestRecording: (Int) -> Unit,
    navigateToQuestBehavior: (Int) -> Unit,
    navigateToQuestRecordingComplete: (Int) -> Unit,
    navigateToQuestTip: (Int) -> Unit,
    navigateToQuestBehaviorComplete: (Int) -> Unit,
    bottomPadding: Dp,
    sharedViewModel: QuestViewModel,
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

        questRecordGraph(
            sharedViewModel = sharedViewModel,
            navController = navController,
            navigateToQuest = navigateToQuest,
            navigateToQuestTip = navigateToQuestTip,
            navigateToQuestRecordingComplete = navigateToQuestRecordingComplete
        )

        questBehaviorGraph(
            sharedViewModel = sharedViewModel,
            navigateToQuest = navigateToQuest,
            navigateToQuestTip = navigateToQuestTip,
            navigateToQuestBehaviorComplete = navigateToQuestBehaviorComplete
        )
    }
}
