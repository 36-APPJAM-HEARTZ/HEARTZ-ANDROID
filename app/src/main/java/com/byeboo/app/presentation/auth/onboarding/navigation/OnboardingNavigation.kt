package com.byeboo.app.presentation.auth.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.navigation.Route
import com.byeboo.app.presentation.auth.onboarding.OnboardingScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToOnboarding(navOptions: NavOptions? = null) {
    navigate(Onboarding, navOptions)
}

fun NavGraphBuilder.onboardingGraph(
    navigateToHome: () -> Unit
) {
    composable<Onboarding> {
        OnboardingScreen(
            navigateToHome = navigateToHome
        )
    }
}

@Serializable
data object Onboarding : Route
