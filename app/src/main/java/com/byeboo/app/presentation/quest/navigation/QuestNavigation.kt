package com.byeboo.app.presentation.quest.navigation

import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.byeboo.app.core.model.quest.QuestType
import com.byeboo.app.core.util.routeNavigation
import com.byeboo.app.presentation.quest.QuestScreen
import com.byeboo.app.presentation.quest.QuestViewModel
import com.byeboo.app.presentation.quest.behavior.QuestBehaviorViewModel
import com.byeboo.app.presentation.quest.behavior.navigation.questBehaviorGraph
import com.byeboo.app.presentation.quest.record.navigation.questRecordGraph
import com.byeboo.app.presentation.quest.review.QuestReviewScreen
import com.byeboo.app.presentation.quest.start.QuestStartScreen
import com.byeboo.app.presentation.quest.tip.QuestTipScreen

fun NavController.navigateToQuestStart(navOptions: NavOptions? = null) {
    navigate(QuestStart, navOptions)
}

fun NavController.navigateToQuest(navOptions: NavOptions? = null) {
    navigate(Quest, navOptions)
}

fun NavController.navigateToQuestTip(
    questId: Long,
    questType: QuestType,
    navOptions: NavOptions? = null
) {
    navigate(QuestTip(questId, questType), navOptions)
}

fun NavController.navigateToQuestReview(questId: Long, navOptions: NavOptions? = null) {
    navigate(QuestReview(questId), navOptions)
}

fun NavGraphBuilder.questGraph(
    navigateUp: () -> Unit,
    navigateToQuest: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToQuestRecording: (Long) -> Unit,
    navigateToQuestBehavior: (Long) -> Unit,
    navigateToQuestReview: (Long) -> Unit,
    navigateToQuestRecordingComplete: (Long) -> Unit,
    navigateToQuestTip: (Long, QuestType) -> Unit,
    navigateToQuestBehaviorComplete: (Long) -> Unit,
    viewModel: QuestBehaviorViewModel,
    padding: Dp
) {
    routeNavigation<Quest, QuestStart> {
        composable<QuestStart> {
            QuestStartScreen(
                navigateToQuest = navigateToQuest,
                navigateToHome = navigateUp,
                padding = padding
            )
        }

        composable<Quest> { backStackEntry ->
            val viewModel = hiltViewModel<QuestViewModel>(backStackEntry)
            QuestScreen(
                viewModel = viewModel,
                navigateToQuestTip = navigateToQuestTip,
                navigateToQuestRecording = navigateToQuestRecording,
                navigateToQuestBehavior = navigateToQuestBehavior,
                navigateToQuestReview = navigateToQuestReview,
                navigateToHome = navigateToHome,
                bottomPadding = padding
            )
        }

        composable<QuestTip> { backStackEntry ->
            val questTip = backStackEntry.toRoute<QuestTip>()
            val questId = questTip.questId
            val questType = questTip.questType

            QuestTipScreen(
                navigateToQuest = navigateUp,
                questId = questId,
                questType = questType,
                bottomPadding = padding
            )
        }

        composable<QuestReview> { backStackEntry ->
            val questReview = backStackEntry.toRoute<QuestReview>()
            val questId = questReview.questId

            QuestReviewScreen(
                questId = questId,
                navigateToBack = navigateUp,
                bottomPadding = padding
            )
        }

        questRecordGraph(
            navigateToQuest = navigateToQuest,
            navigateToQuestTip = navigateToQuestTip,
            navigateToQuestRecordingComplete = navigateToQuestRecordingComplete,
            bottomPadding = padding
        )

        questBehaviorGraph(
            viewModel = viewModel,
            navigateToQuest = navigateToQuest,
            navigateToQuestTip = navigateToQuestTip,
            navigateToQuestBehaviorComplete = navigateToQuestBehaviorComplete,
            bottomPadding = padding
        )
    }
}
