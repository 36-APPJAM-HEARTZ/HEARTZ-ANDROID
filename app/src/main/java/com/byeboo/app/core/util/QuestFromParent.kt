package com.byeboo.app.core.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.byeboo.app.presentation.quest.model.Quest
import com.byeboo.app.presentation.quest.navigation.rememberQuestViewModel

@Composable
fun QuestFromParent(
    questId: Int,
    navController: NavController,
    onQuestReady: (Quest) -> Unit
) {
    val questViewModel = rememberQuestViewModel(navController)
    val questGroups by questViewModel.questGroups.collectAsStateWithLifecycle()

    LaunchedEffect(questId, questGroups) {
        questGroups
            .flatMap { it.quests }
            .find { it.questId == questId }
            ?.let { onQuestReady(it) }
    }
}
