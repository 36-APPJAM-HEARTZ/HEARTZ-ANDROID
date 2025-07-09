package com.byeboo.app.presentation.quest.record

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class QuestRecordingCompleteViewModel @Inject constructor() : ViewModel() {
    private val _state: MutableStateFlow<QuestRecordingCompleteState> =
        MutableStateFlow(QuestRecordingCompleteState())
    val state: StateFlow<QuestRecordingCompleteState>
        get() = _state.asStateFlow()
}