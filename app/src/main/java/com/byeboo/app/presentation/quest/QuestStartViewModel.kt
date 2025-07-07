package com.byeboo.app.presentation.quest

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class QuestStartViewModel @Inject constructor() {
    private val _uiState = MutableStateFlow(QuestStartState())
    val uiState: StateFlow<QuestStartState> = _uiState.asStateFlow()
}