package com.byeboo.app.presentation.auth.loading.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.navigation.Route
import com.byeboo.app.presentation.auth.loading.LoadingScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToLoading(navOptions: NavOptions? = null) {
    navigate(Loading, navOptions)
}

fun NavGraphBuilder.loadingGraph(
    navigateToHome: () -> Unit
) {
    composable<Loading> {
        LoadingScreen(navigateToHome = navigateToHome)
    }
}

@Serializable
data object Loading : Route
