package com.byeboo.app.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.navOptions
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.presentation.home.navigation.Home
import com.byeboo.app.presentation.main.component.MainBottomBar
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
    viewModel: MainViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    var isNavigating by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            MainBottomBar(
                visible = navigator.showBottomBar(),
                tabs = MainNavTab.entries.toImmutableList(),
                currentTab = navigator.currentTab,
                onTabSelected = { selectedTab ->
                    if (isNavigating) return@MainBottomBar

                    scope.launch {
                        isNavigating = true

                        try {
                            val navOptions = navOptions {
                                popUpTo(Home) {
                                    saveState = true
                                    inclusive = false
                                }
                                launchSingleTop = true
                                restoreState = true
                            }

                            if (selectedTab == MainNavTab.QUEST) {
                                val isStarted = viewModel.isQuestStarted()
                                if (isStarted) {
                                    navigator.navigateToQuest(navOptions)
                                } else {
                                    navigator.navigateToQuestStart(navOptions)
                                }
                            } else {
                                navigator.navigate(selectedTab)
                            }
                        } finally {
                            isNavigating = false
                        }
                    }
                }
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(ByeBooTheme.colors.black)
    ) {
        MainNavHost(
            navigator = navigator,
            bottomPadding = it.calculateBottomPadding(),
            modifier = Modifier
        )
    }
}
