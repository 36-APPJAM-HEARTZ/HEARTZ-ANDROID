package com.byeboo.app.presentation.quest.behavior

import androidx.lifecycle.ViewModel
import com.byeboo.app.domain.repository.QuestBehaviorRepository
import com.byeboo.app.presentation.quest.QuestWritingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class QuestBehaviorViewModel @Inject constructor(
    val questBehaviorRepository: QuestBehaviorRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(QuestBehaviorState())
    val uiState: StateFlow<QuestBehaviorState> = _uiState.asStateFlow()
}