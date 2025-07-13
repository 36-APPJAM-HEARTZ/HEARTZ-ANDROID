package com.byeboo.app.presentation.quest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.domain.repository.QuestStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class QuestStartViewModel @Inject constructor(
    private val questStateRepository: QuestStateRepository
) : ViewModel() {
    private val _state = MutableStateFlow(QuestStartState())
    val state: StateFlow<QuestStartState> = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<QuestStartSideEffect>()
    val sideEffect: SharedFlow<QuestStartSideEffect> = _sideEffect

    fun onStartClick() {
        viewModelScope.launch {
            _sideEffect.emit(QuestStartSideEffect.NavigateToQuest)
        }
    }

    fun onBackClick() {
        viewModelScope.launch {
            _sideEffect.emit(QuestStartSideEffect.NavigateToHome)
        }
    }
}
