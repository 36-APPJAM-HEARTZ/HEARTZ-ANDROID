package com.byeboo.app.presentation.quest.behavior

import androidx.lifecycle.ViewModel
import com.byeboo.app.domain.model.ContentLengthValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class QuestBehaviorViewModel @Inject constructor(
//    val questBehaviorRepository: QuestBehaviorRepository
): ViewModel() {


    private val _uiState = MutableStateFlow(QuestBehaviorState())
    val uiState: StateFlow<QuestBehaviorState> = _uiState.asStateFlow()


    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet: StateFlow<Boolean> = _showBottomSheet.asStateFlow()


    fun updateContent(text: String) {
        val contentState = ContentLengthValidator.validate(text)
        _uiState.update { it.copy(
            contents = text,
            contentState = contentState
        )
        }

    }

    fun openBottomSheet() {
        _showBottomSheet.value = true
    }

    fun closeBottomSheet() {
        _showBottomSheet.value = false
    }



}