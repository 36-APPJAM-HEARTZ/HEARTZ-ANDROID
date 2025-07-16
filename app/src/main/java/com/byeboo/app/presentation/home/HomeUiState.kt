package com.byeboo.app.presentation.home

data class HomeUiState(
    val isQuestStarted: Boolean? = false,
    val journey: String = "",
    val dialogue: String = "",
    val currentStep: Int = 0,
    val totalSteps: Int = 30,
    val nickname: String? = "하츠핑"
)
