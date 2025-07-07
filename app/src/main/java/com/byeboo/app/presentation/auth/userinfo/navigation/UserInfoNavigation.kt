package com.byeboo.app.presentation.auth.userinfo.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.navigation.Route
import com.byeboo.app.presentation.auth.userinfo.UserInfoScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToUserInfo(navOptions: NavOptions? = null) {
    navigate(UserInfo, navOptions)
}

fun NavGraphBuilder.userInfoGraph(
    navigateToOnboarding: () -> Unit,
    navigateToLoading: () -> Unit
) {
    composable<UserInfo> {
        UserInfoScreen(
            navigateToOnboarding = navigateToOnboarding,
            navigateToLoading = navigateToLoading
        )
    }
}

@Serializable
data object UserInfo : Route
