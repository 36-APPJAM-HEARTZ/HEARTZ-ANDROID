package com.byeboo.app.presentation.quest.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.util.routeNavigation
import com.byeboo.app.presentation.quest.QuestScreen
import com.byeboo.app.presentation.quest.QuestStartScreen
import com.byeboo.app.presentation.quest.record.navigation.questRecordGraph

fun NavController.navigateToQuestStart(navOptions: NavOptions? = null) {
    navigate(QuestStart, navOptions)
}

fun NavController.navigateToQuest(navOptions: NavOptions? = null) {
    navigate(Quest, navOptions)
}

fun NavGraphBuilder.questGraph(
    questStartBackButton: () -> Unit,
    navigateToQuest: () -> Unit,
    navigateToQuestTip: () -> Unit
) {
    routeNavigation<Quest, QuestStart> {
        composable<QuestStart> {
            QuestStartScreen(
                onNavigateBack = questStartBackButton,
                onNavigateQuest = navigateToQuest
            )
        }

        composable<Quest> {
            QuestScreen()
        }

        questRecordGraph(
            navigateToQuest = navigateToQuest,
            navigateToQuestTip = navigateToQuestTip
        )

        //TODO: questBehaviorGraph 넣어주기
    }
}
