package com.byeboo.app.presentation.home.homeamulet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeAmuletViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeAmuletState())
    val uiState: StateFlow<HomeAmuletState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<HomeAmuletSideEffect>()
    val sideEffect: SharedFlow<HomeAmuletSideEffect> = _sideEffect.asSharedFlow()

    init {
        fetchUserJourney()
    }

    private fun fetchUserJourney() {
        viewModelScope.launch {
            val result = userRepository.getUserJourney()
            if (result.isSuccess) {
                val amulet = result.getOrThrow()
                _uiState.value = HomeAmuletState(
                    journey = AmuletType.from(amulet.journey),
                    journeyDescription = amulet.description,
                    isLoading = false
                )
            }
        }
    }

    fun navigateToHomeOnboarding() {
        viewModelScope.launch {
            _sideEffect.emit(HomeAmuletSideEffect.NavigateToHomeOnboarding)
        }
    }
}
