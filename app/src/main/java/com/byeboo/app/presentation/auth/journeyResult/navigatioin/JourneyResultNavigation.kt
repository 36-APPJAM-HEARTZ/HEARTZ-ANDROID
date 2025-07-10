package com.byeboo.app.presentation.auth.journeyResult.navigatioin

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.navigation.Route
import com.byeboo.app.presentation.auth.journeyResult.JourneyResultScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToJourneyResult(navOptions: NavOptions? = null) {
    navigate(JourneyResult, navOptions)
}

fun NavGraphBuilder.journeyResultGraph() {
    composable<JourneyResult> {
        JourneyResultScreen()
    }
}

@Serializable
data object JourneyResult : Route
