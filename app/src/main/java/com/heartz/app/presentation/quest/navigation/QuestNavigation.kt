package com.heartz.app.presentation.quest.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.heartz.app.core.navigation.MainTabRoute
import com.heartz.app.presentation.quest.QuestScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToQuest(navOptions: NavOptions? = null) {
    navigate(Quest, navOptions)
}

fun NavGraphBuilder.questGraph() {
    composable<Quest> {
        QuestScreen()
    }
}

@Serializable
data object Quest : MainTabRoute
