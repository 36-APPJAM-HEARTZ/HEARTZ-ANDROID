package com.byeboo.app.presentation.main

import android.app.Activity
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.navOptions
import com.byeboo.app.core.designsystem.component.snackbar.ByeBooSnackBar
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.presentation.home.navigation.Home
import com.byeboo.app.presentation.main.component.MainBottomBar
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator(),
    viewModel: MainViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    var isNavigating by remember { mutableStateOf(false) }
    val currentTab = navigator.currentTab

    val onShowSnackBar: (String) -> Unit = { message ->
        scope.launch {
            snackBarHostState.currentSnackbarData?.dismiss()
            val job = launch {
                snackBarHostState.showSnackbar(message)
            }
            delay(3000L)
            job.cancel()
        }
    }

    if (currentTab == MainNavTab.HOME) {
        ByeBooBackHandler(
            context = context,
            enabled = true,
            exitDelayMillis = 3000L,
            onShowSnackBar = {
                onShowSnackBar("뒤로가기를 한 번 더 누르면 앱이 종료됩니다")
            }
        )
    } else {
        BackHandler(enabled = true) {
            navigator.navigate(MainNavTab.HOME)
        }
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier.fillMaxWidth()
            ) { data ->
                ByeBooSnackBar(
                    text = data.visuals.message,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        bottomBar = {
            MainBottomBar(
                visible = navigator.showBottomBar(),
                tabs = MainNavTab.entries.toImmutableList(),
                currentTab = currentTab,
                onTabSelected = { selectedTab ->
                    if (isNavigating || selectedTab == currentTab) return@MainBottomBar
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
            padding = it.calculateBottomPadding(),
            modifier = Modifier
        )
    }

}

@Composable
fun ByeBooBackHandler(
    context: Context,
    enabled: Boolean = true,
    exitDelayMillis: Long = 3000L,
    onShowSnackBar: () -> Unit = {}
) {
    var backPressedTime by remember {
        mutableLongStateOf(0L)
    }

    BackHandler(enabled = enabled) {
        if (System.currentTimeMillis() - backPressedTime <= exitDelayMillis) {
            (context as Activity).finish()
        } else {
            onShowSnackBar()
        }
        backPressedTime = System.currentTimeMillis()
    }
}