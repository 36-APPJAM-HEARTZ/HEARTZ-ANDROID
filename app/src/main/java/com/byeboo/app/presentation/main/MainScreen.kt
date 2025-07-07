package com.byeboo.app.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.byeboo.app.presentation.main.component.MainBottomBar
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainScreen(navigator: MainNavigator = rememberMainNavigator()) {
    Scaffold(
        bottomBar = {
            MainBottomBar(
                visible = navigator.showBottomBar(),
                tabs = MainNavTab.entries.toImmutableList(),
                currentTab = navigator.currentTab,
                onTabSelected = navigator::navigate
            )
        },
        modifier =
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        MainNavHost(
            navigator = navigator,
            bottomPadding = it.calculateBottomPadding(),
            modifier = Modifier
        )
    }
}
