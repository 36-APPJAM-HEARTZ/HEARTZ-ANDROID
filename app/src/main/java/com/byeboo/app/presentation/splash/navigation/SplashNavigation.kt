package com.byeboo.app.presentation.splash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.navigation.Route
import com.byeboo.app.presentation.splash.SplashScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToSplash(navOptions: NavOptions? = null) {
    navigate(Splash, navOptions)
}

fun NavGraphBuilder.splashGraph(
    navigateToHome: () -> Unit,
    navigateToOnboarding: () -> Unit
) {
    composable<Splash> {
        SplashScreen(
            navigateToHome = navigateToHome,
            navigateToOnboarding = navigateToOnboarding
        )
    }
}

@Serializable
data object Splash : Route
