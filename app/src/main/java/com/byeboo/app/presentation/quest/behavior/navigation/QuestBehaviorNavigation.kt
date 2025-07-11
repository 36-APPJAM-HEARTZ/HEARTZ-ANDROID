package com.byeboo.app.presentation.quest.behavior.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.util.routeNavigation
import com.byeboo.app.presentation.quest.QuestViewModel
import com.byeboo.app.presentation.quest.behavior.QuestBehaviorCompleteScreen
import com.byeboo.app.presentation.quest.behavior.QuestBehaviorWritingScreen
import com.byeboo.app.presentation.quest.behavior.navigation.QuestBehavior.QuestBehaviorComplete
import com.byeboo.app.presentation.quest.behavior.navigation.QuestBehavior.QuestBehaviorWriting

fun NavController.navigateToQuestBehavior(questId: Int, navOptions: NavOptions? = null) {
    navigate(QuestBehaviorWriting(questId), navOptions)
}

fun NavController.navigateToQuestBehaviorComplete(questId: Int, navOptions: NavOptions? = null) {
    navigate(QuestBehaviorComplete(questId), navOptions)
}

fun NavGraphBuilder.questBehaviorGraph(
    navigateToQuest: () -> Unit,
    navigateToQuestTip: (Int) -> Unit,
    navigateToQuestBehaviorComplete: (Int) -> Unit,
    sharedViewModel: QuestViewModel
) {
    routeNavigation<QuestBehavior, QuestBehaviorWriting> {
        composable<QuestBehaviorWriting> {
            QuestBehaviorWritingScreen(
                navigateToQuest = navigateToQuest,
                navigateToQuestTip = navigateToQuestTip,
                navigateToQuestBehaviorComplete = navigateToQuestBehaviorComplete,
                sharedViewModel = sharedViewModel
            )
        }

        composable<QuestBehaviorComplete> {
            QuestBehaviorCompleteScreen(
                navigateToQuest = navigateToQuest,
                sharedViewModel = sharedViewModel
            )
        }
    }
}