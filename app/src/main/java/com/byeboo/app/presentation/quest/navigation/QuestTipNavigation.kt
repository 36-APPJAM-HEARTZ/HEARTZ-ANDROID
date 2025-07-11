package com.byeboo.app.presentation.quest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.util.routeNavigation
import com.byeboo.app.presentation.quest.QuestTipScreen

fun NavController.navigateToQuestTip(questId: Int, navOptions: NavOptions? = null) {
    navigate(QuestTip(questId), navOptions)
}

fun NavGraphBuilder.questTipGraph(
    navigateBack: () -> Unit
) {
    composable<QuestTip> {
        QuestTipScreen(navigateBack = navigateBack)
    }
}