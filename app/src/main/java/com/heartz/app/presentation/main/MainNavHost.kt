package com.heartz.app.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.heartz.app.presentation.graph.navigation.figureGraph
import com.heartz.app.presentation.home.navigation.homeGraph
import com.heartz.app.presentation.mypage.navigation.mypageGraph
import com.heartz.app.presentation.quest.navigation.questGraph

@Composable
fun MainNavHost(
    navigator: MainNavigator,
    modifier: Modifier = Modifier
) {
    NavHost(
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
        navController = navigator.navController,
        startDestination = navigator.startDestination
    ) {
        homeGraph()
        questGraph()
        figureGraph()
        mypageGraph()
    }
}
