package com.byeboo.app.presentation.auth.navigation

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.navigation.Route
import com.byeboo.app.presentation.auth.loading.LoadingScreen
import com.byeboo.app.presentation.auth.onboarding.OnboardingScreen
import com.byeboo.app.presentation.auth.userinfo.UserInfoScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToOnboarding(navOptions: NavOptions? = null) {
    navigate(Onboarding, navOptions)
}

fun NavController.navigateToUserInfo(navOptions: NavOptions? = null) {
    navigate(UserInfo, navOptions)
}

fun NavController.navigateToLoading(navOptions: NavOptions? = null) {
    navigate(Loading, navOptions)
}

fun NavGraphBuilder.authGraph(
    navigateToUserInfo: () -> Unit,
    navigateToOnboarding: () -> Unit,
    navigateToLoading: () -> Unit,
    navigateToHomeAmulet: () -> Unit,
    bottomPadding: Dp
) {
    composable<Onboarding> {
        OnboardingScreen(
            navigateToUserInfo = navigateToUserInfo,
            padding = bottomPadding
        )
    }
    composable<UserInfo> {
        UserInfoScreen(
            navigateToOnboarding = navigateToOnboarding,
            navigateToLoading = navigateToLoading,
            padding = bottomPadding
        )
    }
    composable<Loading> {
        LoadingScreen(
            navigateToHomeAmulet = navigateToHomeAmulet
        )
    }
}

@Serializable
data object Onboarding : Route

@Serializable
data object UserInfo : Route

@Serializable
data object Loading : Route