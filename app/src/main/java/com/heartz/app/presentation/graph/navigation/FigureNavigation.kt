package com.heartz.app.presentation.graph.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.heartz.app.core.navigation.MainTabRoute
import com.heartz.app.presentation.graph.FigureScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToFigure(navOptions: NavOptions? = null) {
    navigate(Figure, navOptions)
}

fun NavGraphBuilder.figureGraph() {
    composable<Figure> {
        FigureScreen()
    }
}

@Serializable
data object Figure : MainTabRoute
