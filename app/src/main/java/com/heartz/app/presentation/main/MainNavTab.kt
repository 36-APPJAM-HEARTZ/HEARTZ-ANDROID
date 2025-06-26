package com.heartz.app.presentation.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.heartz.app.R.drawable.ic_folder_open
import com.heartz.app.R.drawable.ic_graph
import com.heartz.app.R.drawable.ic_home
import com.heartz.app.R.drawable.ic_user
import com.heartz.app.R.string.ic_figure_desc
import com.heartz.app.R.string.ic_home_desc
import com.heartz.app.R.string.ic_mypage_desc
import com.heartz.app.R.string.ic_quest_desc
import com.heartz.app.core.navigation.MainTabRoute
import com.heartz.app.core.navigation.Route
import com.heartz.app.presentation.graph.navigation.Figure
import com.heartz.app.presentation.home.navigation.Home
import com.heartz.app.presentation.mypage.navigation.Mypage
import com.heartz.app.presentation.quest.navigation.Quest

enum class MainNavTab(
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int,
    val route: MainTabRoute
) {
    QUEST(
        icon = ic_folder_open,
        contentDescription = ic_quest_desc,
        route = Quest
    ),
    HOME(
        icon = ic_home,
        contentDescription = ic_home_desc,
        route = Home
    ),
    FIGURE(
        icon = ic_graph,
        contentDescription = ic_figure_desc,
        route = Figure
    ),
    MYPAGE(
        icon = ic_user,
        contentDescription = ic_mypage_desc,
        route = Mypage
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainNavTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
