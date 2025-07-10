package com.byeboo.app.presentation.quest.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.presentation.quest.QuestTipScreen

fun NavController.navigateToQuestTip(navOptions: NavOptions? = null) {
    navigate(QuestTip, navOptions)
}

fun NavGraphBuilder.questTipGraph(
    navigateBack: () -> Unit
){
    composable<QuestTip> {
        QuestTipScreen(navigateBack = navigateBack)
    }
}