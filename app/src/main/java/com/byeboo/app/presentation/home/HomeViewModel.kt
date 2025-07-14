package com.byeboo.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.domain.repository.quest.QuestStateRepository
import com.byeboo.app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val questStateRepository: QuestStateRepository
) : ViewModel() {

    val nickname: StateFlow<String?> = userRepository.getNickname()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(30000), null)

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
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
                        val newState = current.copy(
                            isQuestStarted = isStarted,
                            journey = journey,
                            dialogue = dialogue
                        )
                        if (current != newState) newState else current
                    }
                }
            }
        }
    }
}
