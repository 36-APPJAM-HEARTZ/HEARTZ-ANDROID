package com.byeboo.app.presentation.quest.record

import androidx.lifecycle.ViewModel
import com.byeboo.app.presentation.quest.QuestWritingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuestRecordingViewModel @Inject constructor() : ViewModel() {
    private val _state: MutableStateFlow<QuestRecordingState> =
        MutableStateFlow(QuestRecordingState())
    val state: StateFlow<QuestRecordingState>
        get() = _state.asStateFlow()

    private val _writingState = MutableStateFlow<QuestWritingState>(
        QuestWritingState.BeforeWriting
    )
    val writingState: StateFlow<QuestWritingState> get() = _writingState

    fun updateContent(text: String) {
        val contentState = when {
            text.isEmpty() -> QuestWritingState.BeforeWriting
            text.length > 500 -> QuestWritingState.OverLimit
            else -> QuestWritingState.Writing
        }
        _writingState.value = contentState
        _state.update { it.copy(contents = text) }
    }
}