package com.byeboo.app.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.byeboo.app.presentation.auth.navigation.Onboarding
import com.byeboo.app.presentation.auth.navigation.navigateToLoading
import com.byeboo.app.presentation.auth.navigation.navigateToOnboarding
import com.byeboo.app.presentation.auth.navigation.navigateToUserInfo
import com.byeboo.app.presentation.home.navigation.navigateToHome
import com.byeboo.app.presentation.home.navigation.navigateToHomeAmulet
import com.byeboo.app.presentation.home.navigation.navigateToHomeOnboarding
import com.byeboo.app.presentation.mypage.navigation.navigateToMypage
import com.byeboo.app.presentation.quest.navigation.navigateToQuest
import com.byeboo.app.presentation.quest.navigation.navigateToQuestComplete

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() =
            navController
                .currentBackStackEntryAsState().value?.destination

    val startDestination = Onboarding

    val currentTab: MainNavTab?
        @Composable get() =
            MainNavTab.find { tab ->
                currentDestination?.hasRoute(tab::class) == true
            }

    fun navigate(tab: MainNavTab) {
        val navOptions =
            navOptions {
                navController.currentDestination?.route?.let {
                    popUpTo(it) {
                        inclusive = true
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        when (tab) {
            MainNavTab.QUEST -> navController.navigateToQuest(navOptions)
            MainNavTab.HOME -> navController.navigateToHome(navOptions)
            MainNavTab.MYPAGE -> navController.navigateToMypage(navOptions)
        }
    }

    @Composable
    fun showBottomBar() =
        MainNavTab.contains {
            currentDestination?.hasRoute(it::class) == true
        }

    fun navigateToOnboarding(navOptions: NavOptions) {
        navController.navigateToOnboarding(navOptions)
    }

    fun navigateToHome(navOptions: NavOptions) {
        navController.navigateToHome(navOptions)
    }

    fun navigateToMypage(navOptions: NavOptions? = null) {
        navController.navigateToMypage(navOptions)
    }

    fun navigateToLoading(navOptions: NavOptions) {
        navController.navigateToLoading(navOptions)
    }
    fun navigateToUserInfo(navOptions: NavOptions) {
        navController.navigateToUserInfo(navOptions)
    }

    fun navigateToQuestComplete(navOptions: NavOptions) {
        navController.navigateToQuestComplete(navOptions)
    }

    fun navigateToHomeOnboarding(navOptions: NavOptions) {
        navController.navigateToHomeOnboarding(navOptions)
    }

    fun navigateToHomeAmulet(navOptions: NavOptions) {
        navController.navigateToHomeAmulet(navOptions)
    }

    fun navigateUp() {
        navController.navigateUp()
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
