package com.byeboo.app.presentation.quest.behavior

import androidx.lifecycle.ViewModel
import com.byeboo.app.domain.model.ContentLengthValidator
import com.byeboo.app.presentation.quest.QuestRecordingState
import com.byeboo.app.presentation.quest.QuestWritingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.Int

@HiltViewModel
class QuestBehaviorViewModel @Inject constructor(
//    val questBehaviorRepository: QuestBehaviorRepository
): ViewModel() {


    private val _uiState: MutableStateFlow<QuestBehaviorState> = MutableStateFlow(QuestBehaviorState())

    val uiState: StateFlow<QuestBehaviorState>
        get() = _uiState.asStateFlow()


    fun updateContent(text: String) {
        val contentState = ContentLengthValidator.validate(text)
        _uiState.update { it.copy(
            contents = text,
            contentState = contentState
        )
        }

    }



}