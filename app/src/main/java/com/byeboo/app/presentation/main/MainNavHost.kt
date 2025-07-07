package com.byeboo.app.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.byeboo.app.presentation.auth.onboarding.navigation.onboardingGraph
import com.byeboo.app.presentation.home.navigation.homeGraph
import com.byeboo.app.presentation.mypage.navigation.mypageGraph
import com.byeboo.app.presentation.quest.behavior.navigation.questBehaviorWritingGraph
import com.byeboo.app.presentation.quest.navigation.questGraph
import com.byeboo.app.presentation.splash.navigation.splashGraph

@Composable
fun MainNavHost(
    navigator: MainNavigator,
    modifier: Modifier = Modifier
) {
    val clearStackNavOptions = navOptions {
        popUpTo(0) { inclusive = true }
        launchSingleTop = true
    }
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
        onboardingGraph(
            navigateToHome = {
                navigator.navigateToHome(clearStackNavOptions)
            }
        )
        questBehaviorWritingGraph(
            navigateToQuestComplete = {
                navigator.navigateToQuestComplete(clearStackNavOptions)
            }
        )
        homeGraph()
        questGraph()
        mypageGraph()
    }
}
