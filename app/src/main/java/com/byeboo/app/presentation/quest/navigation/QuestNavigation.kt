package com.byeboo.app.presentation.quest.navigation

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.navigation.MainTabRoute
import com.byeboo.app.presentation.quest.QuestScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToQuest(navOptions: NavOptions? = null) {
    navigate(Quest, navOptions)
}

fun NavGraphBuilder.questGraph(
    navigateToHome: () -> Unit,
    navigateToMypage: () -> Unit,
    bottomPadding: Dp
) {
    composable<Quest> {
        QuestScreen(
            navigateToHome = navigateToHome,
            navigateToMypage = navigateToMypage,
            bottomPadding = bottomPadding
        )
    }
}

@Serializable
data object Quest : MainTabRoute
