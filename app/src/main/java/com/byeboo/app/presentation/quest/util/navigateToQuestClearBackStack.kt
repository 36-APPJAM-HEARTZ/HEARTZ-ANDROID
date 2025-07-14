package com.byeboo.app.presentation.quest.util

import androidx.navigation.NavController
import com.byeboo.app.presentation.quest.navigation.Quest

fun NavController.navigateToQuestClearBackStack() {
    navigate(Quest) {
        popUpTo(graph.id) { inclusive = true }
        launchSingleTop = true
    }
}