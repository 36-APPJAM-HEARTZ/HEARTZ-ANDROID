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
import com.byeboo.app.presentation.auth.loading.navigation.navigateToLoading
import com.byeboo.app.presentation.auth.onboarding.navigation.navigateToOnboarding
import com.byeboo.app.presentation.auth.userinfo.navigation.UserInfo
import com.byeboo.app.presentation.auth.userinfo.navigation.navigateToUserInfo
import com.byeboo.app.presentation.home.navigation.navigateToHome
import com.byeboo.app.presentation.mypage.navigation.navigateToMyPage
import com.byeboo.app.presentation.quest.navigation.navigateToQuest
import com.byeboo.app.presentation.quest.navigation.navigateToQuestStart
import com.byeboo.app.presentation.quest.navigation.navigateToQuestTip
import com.byeboo.app.presentation.quest.record.navigation.navigateToQuestRecord

class MainNavigator(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() =
            navController
                .currentBackStackEntryAsState().value?.destination

    val startDestination = UserInfo

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
            MainNavTab.QUEST -> navController.navigateToQuestStart(navOptions)
            MainNavTab.HOME -> navController.navigateToHome(navOptions)
            MainNavTab.MYPAGE -> navController.navigateToMyPage(navOptions)
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

    fun navigateToUserInfo(navOptions: NavOptions) {
        navController.navigateToUserInfo(navOptions)
    }

    fun navigateToHome(navOptions: NavOptions) {
        navController.navigateToHome(navOptions)
    }

    fun navigateToLoading(navOptions: NavOptions) {
        navController.navigateToLoading(navOptions)
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateToQuest(navOptions: NavOptions) {
        navController.navigateToQuest(navOptions)
    }

    fun navigateToQuestTip(navOptions: NavOptions? = null) {
        navController.navigateToQuestTip(navOptions)
    }

    fun navigateToQuestRecord(navOptions: NavOptions? = null) {
        navController.navigateToQuestRecord(navOptions)
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
