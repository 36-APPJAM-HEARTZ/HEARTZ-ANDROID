package com.byeboo.app.presentation.quest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.domain.repository.UserRepository
import com.byeboo.app.domain.repository.quest.QuestStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class QuestStartViewModel @Inject constructor(
    private val questStateRepository: QuestStateRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(QuestStartState())
    val uiState: StateFlow<QuestStartState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<QuestStartSideEffect>()
    val sideEffect: SharedFlow<QuestStartSideEffect> = _sideEffect

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            userRepository.getNickname().collect { name ->
                _uiState.update { it.copy(nickname = name) }
            }
        }
        viewModelScope.launch {
            val journey = questStateRepository.getUserJourney() ?: "감정 직면"
            _uiState.update { it.copy(journeyName = journey) }
        }
    }

    fun onStartClick() {
        viewModelScope.launch {
            questStateRepository.updateQuestState()
            questStateRepository.setQuestStarted(true)
            _sideEffect.emit(QuestStartSideEffect.NavigateToQuest)
        }
    }

    fun onBackClick() {
        viewModelScope.launch {
            _sideEffect.emit(QuestStartSideEffect.NavigateToHome)
        }
    }
}
