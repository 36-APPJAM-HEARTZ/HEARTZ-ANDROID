package com.byeboo.app.presentation.quest.navigation

import com.byeboo.app.core.model.QuestType
import com.byeboo.app.core.navigation.MainTabRoute
import com.byeboo.app.core.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
data object Quest : MainTabRoute {
    const val ROUTE = "quest"
}

@Serializable
data object QuestStart : Route

@Serializable
data class QuestTip(val questId: Long) : Route

@Serializable
data class QuestReview(val questId: Long) : Route
