package com.heartz.app.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.heartz.app.presentation.home.navigation.homeGraph
import com.heartz.app.presentation.mypage.navigation.mypageGraph
import com.heartz.app.presentation.onboarding.navigation.onboardingGraph
import com.heartz.app.presentation.quest.navigation.questGraph
import com.heartz.app.presentation.splash.navigation.splashGraph

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
        homeGraph()
        questGraph()
        mypageGraph()
    }
}
