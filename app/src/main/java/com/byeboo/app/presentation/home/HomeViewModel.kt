package com.byeboo.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.domain.repository.quest.QuestStateRepository
import com.byeboo.app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val questStateRepository: QuestStateRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch {
            userRepository.getNickname().collect { name ->
                _uiState.update { it.copy(nickname = name) }
            }
        }
        viewModelScope.launch {
            val isStarted = questStateRepository.isQuestStarted()
            val journey = questStateRepository.getUserJourney() ?: "감정 직면"

            val dialogueResult = questStateRepository.getQuestDialogue()
            val dialogue = dialogueResult.getOrNull()?.dialogue
                ?: "천천히, 하지만 분명하게. 오늘도 나아가 봐요."

            _uiState.update {
                it.copy(isQuestStarted = isStarted, journey = journey, dialogue = dialogue)
            }

            if (isStarted) {
                questStateRepository.getQuestCount().collect { model ->
                    _uiState.update { current ->
                        current.copy(
                            isQuestStarted = isStarted,
                            journey = journey,
                            dialogue = dialogue,
                            currentStep = model.count,
                            totalSteps = 30
                        )
                    }
                }
            }
        }
    }
}
