package com.byeboo.app.presentation.quest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestTipViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(QuestTipState())
    val state: StateFlow<QuestTipState>
        get() = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<QuestTipSideEffect>()
    val sideEffect: SharedFlow<QuestTipSideEffect> = _sideEffect

    fun onCloseClick(){
        viewModelScope.launch {
            _sideEffect.emit(QuestTipSideEffect.NavigateBack)
        }
    }
}