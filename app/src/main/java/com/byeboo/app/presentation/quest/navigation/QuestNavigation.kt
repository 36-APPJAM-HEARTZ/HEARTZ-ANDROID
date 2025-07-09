package com.byeboo.app.presentation.quest.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.navigation.MainTabRoute
import com.byeboo.app.presentation.quest.QuestScreen
import com.byeboo.app.presentation.quest.behavior.QuestBehaviorWritingScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToQuest(navOptions: NavOptions? = null) {
    navigate(Quest, navOptions)
}

fun NavGraphBuilder.questGraph() {
    composable<Quest> {
        //QuestScreen()
        QuestBehaviorWritingScreen()
    }
}

@Serializable
data object Quest : MainTabRoute
