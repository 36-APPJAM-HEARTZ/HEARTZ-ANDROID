package com.heartz.app.presentation.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.heartz.app.core.navigation.MainTabRoute
import com.heartz.app.presentation.home.HomeScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(Home, navOptions)
}

fun NavGraphBuilder.homeGraph() {
    composable<Home> {
        HomeScreen()
    }
}

@Serializable
data object Home : MainTabRoute
