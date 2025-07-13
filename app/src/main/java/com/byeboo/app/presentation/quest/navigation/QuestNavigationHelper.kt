package com.byeboo.app.presentation.quest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.byeboo.app.presentation.quest.QuestViewModel

@Composable
fun rememberQuestViewModel(navController: NavController): QuestViewModel {
    val parentEntry = remember(navController.currentBackStackEntry) {
        navController.getBackStackEntry("quest")
    }
    return hiltViewModel(parentEntry)
}
