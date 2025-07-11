package com.byeboo.app.presentation.mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.byeboo.app.core.navigation.MainTabRoute
import com.byeboo.app.presentation.mypage.MyPageScreen
import kotlinx.serialization.Serializable

fun NavController.navigateToMyPage(navOptions: NavOptions? = null) {
    navigate(MyPage, navOptions)
}

fun NavGraphBuilder.myPageGraph() {
    composable<MyPage> {
        MyPageScreen(
            navigateToCompletedJourney = {}
        )
    }
}

@Serializable
data object MyPage : MainTabRoute
