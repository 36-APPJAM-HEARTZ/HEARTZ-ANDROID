package com.byeboo.app.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.byeboo.app.presentation.auth.onboarding.navigation.onboardingGraph
import com.byeboo.app.presentation.auth.userinfo.navigation.authGraph
import com.byeboo.app.presentation.home.navigation.homeGraph
import com.byeboo.app.presentation.mypage.navigation.myPageGraph
import com.byeboo.app.presentation.quest.behavior.QuestBehaviorViewModel
import com.byeboo.app.presentation.quest.navigation.questTipGraph
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
    val sharedViewModel: QuestBehaviorViewModel = hiltViewModel()
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
            navigateToHome = { navigator.navigateToHome(clearStackNavOptions) },
            navigateToOnboarding = { navigator.navigateToOnboarding(clearStackNavOptions) }
        )
        onboardingGraph(
            navigateToUserInfo = {
                navigator.navigateToUserInfo(clearStackNavOptions)
            }
        )
        authGraph(
            navigateToUserInfo = {
                navigator.navigateToUserInfo(clearStackNavOptions)
            },
            navigateToOnboarding = {
                navigator.navigateToOnboarding(clearStackNavOptions)
            },
            navigateToLoading = {
                navigator.navigateToLoading(clearStackNavOptions)
            },
            navigateToHomeOnboarding = {
                navigator.navigateToHomeOnboarding(clearStackNavOptions)
            }
        )
        homeGraph(
            bottomPadding = bottomPadding,
            navigateToHome = {
                navigator.navigateToHome(clearStackNavOptions)
            }
        )
//        questGraph(
//            questStartBackButton = { navigator.navigateToHome(clearStackNavOptions) },
//            navigateToQuest = { navigator.navigateToQuestRecord(clearStackNavOptions) },
//            navigateToQuestTip = {navigator.navigateToQuestTip()}
//        )
//
//        questGraph(
//            sharedViewModel = sharedViewModel,
//            navigateToQuestComplete = {
//                navigator.navigateToQuestComplete(clearStackNavOptions)
//            }
//        )
        myPageGraph()
        questTipGraph(navigateBack = {navigator.navController.popBackStack()})


    }
}
