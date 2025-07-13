package com.byeboo.app.presentation.quest.record

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.presentation.quest.QuestViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestRecordingCompleteViewModel @Inject constructor(
) : ViewModel() {
    private val _state = MutableStateFlow(QuestRecordingCompleteState())
    val state: StateFlow<QuestRecordingCompleteState>
        get() = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<QuestRecordingCompleteSideEffect>()
    val sideEffect: SharedFlow<QuestRecordingCompleteSideEffect> = _sideEffect

    fun onCloseClick() {
        viewModelScope.launch {
            _sideEffect.emit(QuestRecordingCompleteSideEffect.NavigateToQuest)
        }
    }
}