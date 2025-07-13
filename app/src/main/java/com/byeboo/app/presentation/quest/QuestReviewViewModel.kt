package com.byeboo.app.presentation.quest

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class QuestReviewViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(QuestReviewState())
    val uiState: StateFlow<QuestReviewState>
        get() = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<QuestReviewSideEffect>()
    val sideEffect: SharedFlow<QuestReviewSideEffect>
        get() = _sideEffect

    private val _selectedImageUri = MutableStateFlow<Uri?>(null)
    val selectedImageUri: StateFlow<Uri?> = _selectedImageUri

    fun setQuestId(questId: Long) {
        _uiState.update {
            it.copy(questId = questId)
        }
    }

    fun onCloseClick() {
        viewModelScope.launch {
            _sideEffect.emit(QuestReviewSideEffect.NavigateToQuest)
        }
    }
}
