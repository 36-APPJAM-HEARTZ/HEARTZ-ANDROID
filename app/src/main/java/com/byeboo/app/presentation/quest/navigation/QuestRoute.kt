package com.byeboo.app.presentation.quest.navigation

import com.byeboo.app.core.navigation.MainTabRoute
import com.byeboo.app.core.navigation.Route
import kotlinx.serialization.Serializable

@Serializable
data object Quest: MainTabRoute

@Serializable
data object QuestStart: Route

@Serializable
data object QuestTip: Route
