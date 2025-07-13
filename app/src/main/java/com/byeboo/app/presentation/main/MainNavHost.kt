package com.byeboo.app.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.byeboo.app.presentation.auth.navigation.authGraph
import com.byeboo.app.presentation.home.navigation.homeGraph
import com.byeboo.app.presentation.mypage.navigation.myPageGraph
import com.byeboo.app.presentation.quest.QuestViewModel
import com.byeboo.app.presentation.quest.navigation.questGraph
import com.byeboo.app.presentation.splash.navigation.splashGraph

@Composable
fun MainNavHost(
    navigator: MainNavigator,
    bottomPadding: Dp,
    modifier: Modifier = Modifier
) {
    val clearStackNavOptions = navOptions {
        popUpTo(0) { inclusive = true }
        launchSingleTop = true
    }
    val sharedViewModel: QuestViewModel = hiltViewModel()

    NavHost(
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
        navController = navigator.navController,
        startDestination = navigator.startDestination
    ) {
        splashGraph(
            navigateToHome = {
                navigator.navigateToHome(clearStackNavOptions)
            },
            navigateToOnboarding = {
                navigator.navigateToOnboarding(clearStackNavOptions)
            }
        )
        authGraph(
            navigateToUserInfo = {
                navigator.navigateToUserInfo(clearStackNavOptions)
            },
            navigateToLoading = {
                navigator.navigateToLoading(clearStackNavOptions)
            },
            navigateToHomeAmulet = {
                navigator.navigateToHomeAmulet(clearStackNavOptions)
            },
            bottomPadding = bottomPadding
        )
        homeGraph(
            bottomPadding = bottomPadding,
            navigateToHome = {
                navigator.navigateToHome(clearStackNavOptions)
            },
            navigateToHomeOnboarding = {
                navigator.navigateToHomeOnboarding(clearStackNavOptions)
            },

        )
        questGraph(
            navController = navigator.navController,
            questStartBackButton = { navigator.navigateToHome(clearStackNavOptions) },
            navigateToQuest = { navigator.navigateToQuest(clearStackNavOptions) },
            navigateToQuestRecording = { questId -> navigator.navigateToQuestRecording(questId) },
            navigateToQuestBehavior = { questId -> navigator.navigateToQuestBehavior(questId) },
            navigateToQuestRecordingComplete = { questId ->
                navigator.navigateToQuestRecordingComplete(
                    questId,
                    clearStackNavOptions
                )
            },
            navigateToQuestTip = { questId -> navigator.navigateToQuestTip(questId) },
            navigateToQuestBehaviorComplete = { questId ->
                navigator.navigateToQuestBehaviorComplete(
                    questId,
                    clearStackNavOptions
                )
            },
            bottomPadding = bottomPadding,
            sharedViewModel = sharedViewModel
        )
        myPageGraph()
    }
}
