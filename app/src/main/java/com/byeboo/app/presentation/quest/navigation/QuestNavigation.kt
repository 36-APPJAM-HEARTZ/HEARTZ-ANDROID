package com.byeboo.app.presentation.quest.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.navigation.MainTabRoute
import com.byeboo.app.core.navigation.Route
import com.byeboo.app.presentation.quest.behavior.QuestBehaviorCompleteScreen
import com.byeboo.app.presentation.quest.behavior.QuestBehaviorViewModel
import com.byeboo.app.presentation.quest.behavior.QuestBehaviorWritingReviewScreen
import com.byeboo.app.presentation.quest.behavior.QuestBehaviorWritingScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToQuest(navOptions: NavOptions? = null) {
    navigate(Quest, navOptions)
}
fun NavController.navigateToQuestComplete(navOptions: NavOptions? = null) {
    navigate(QuestComplete, navOptions)
}
fun NavController.navigateToQuestReview(navOptions: NavOptions? = null) {
    navigate(QuestReview, navOptions)
}
fun NavGraphBuilder.questGraph(
    sharedViewModel: QuestBehaviorViewModel,
    navigateToQuestComplete: () -> Unit,
    navigateToQuestReview: () -> Unit
) {
    composable<Quest> {
        QuestBehaviorWritingScreen(
            sharedViewModel = sharedViewModel,
            navigateToQuestComplete = navigateToQuestComplete
        )
    }
    composable<QuestComplete> {
        QuestBehaviorCompleteScreen(
            sharedViewModel = sharedViewModel
        )
    }
    composable<QuestReview> {
        QuestBehaviorWritingReviewScreen(
            sharedViewModel = sharedViewModel
        )
    }
}

@Serializable
data object Quest : MainTabRoute

@Serializable
data object QuestComplete : Route

@Serializable
data object QuestReview: Route
