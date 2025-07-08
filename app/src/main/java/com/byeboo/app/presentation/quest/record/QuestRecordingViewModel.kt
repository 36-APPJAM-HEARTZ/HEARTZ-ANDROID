package com.byeboo.app.presentation.quest.record

import androidx.lifecycle.ViewModel
import com.byeboo.app.domain.model.QuestContentLengthValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class
QuestRecordingViewModel @Inject constructor() : ViewModel() {
    private val _state: MutableStateFlow<QuestRecordingState> =
        MutableStateFlow(QuestRecordingState())
    val state: StateFlow<QuestRecordingState>
        get() = _state.asStateFlow()

    fun updateContent(text: String) {
        val contentState = QuestContentLengthValidator.validate(text)
        _state.update {
            it.copy(
                contents = text,
                contentsState = contentState
            )
        }
    }
}