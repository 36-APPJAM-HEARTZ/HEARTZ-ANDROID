package com.byeboo.app.presentation.home.navigation

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.navigation.MainTabRoute
import com.byeboo.app.core.navigation.Route
import com.byeboo.app.presentation.home.HomeScreen
import com.byeboo.app.presentation.home.homeonboarding.HomeOnboardingScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(Home, navOptions)
}

fun NavController.navigateToHomeOnboarding(navOptions: NavOptions? = null) {
    navigate(HomeOnboarding, navOptions)
}

fun NavGraphBuilder.homeGraph(
    bottomPadding: Dp,
    navigateToHome: () -> Unit
) {
    composable<Home> {
        HomeScreen()
    }
    composable<HomeOnboarding> {
        HomeOnboardingScreen(
            bottomPadding = bottomPadding,
            navigateToHome = navigateToHome
        )
    }
}

@Serializable
data object Home : MainTabRoute

@Serializable
data object HomeOnboarding : Route
