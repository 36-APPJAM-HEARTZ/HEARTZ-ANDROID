package com.byeboo.app.presentation.home.homeamulet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class HomeAmuletViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeAmuletState())
    val uiState: StateFlow<HomeAmuletState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<JourneyResultSideEffect>()
    val sideEffect: SharedFlow<JourneyResultSideEffect> = _sideEffect.asSharedFlow()

    val nickname: StateFlow<String?> = userRepository.getNickname()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
}
