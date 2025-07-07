package com.byeboo.app.presentation.quest.behavior.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.navigation.Route
import com.byeboo.app.presentation.quest.behavior.QuestBehaviorWritingScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToQuestBehaviorWriting(navOptions: NavOptions? = null) {
    navigate(QuestBehaviorWriting, navOptions)
}

fun NavGraphBuilder.questBehaviorWritingGraph(
    navigateToQuestComplete: () -> Unit) {
    composable<QuestBehaviorWriting> {
        QuestBehaviorWritingScreen(navigateToQuestComplete = navigateToQuestComplete)
    }
}


@Serializable
data object QuestBehaviorWriting : Route