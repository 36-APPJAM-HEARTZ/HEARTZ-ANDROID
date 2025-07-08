package com.byeboo.app.presentation.quest

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class QuestTipViewModel @Inject constructor() : ViewModel() {
    private val _state: MutableStateFlow<QuestTipState> = MutableStateFlow(QuestTipState())
    val state: StateFlow<QuestTipState>
        get() = _state.asStateFlow()
}