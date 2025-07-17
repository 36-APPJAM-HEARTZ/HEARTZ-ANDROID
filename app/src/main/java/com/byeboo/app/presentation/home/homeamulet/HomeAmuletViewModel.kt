package com.byeboo.app.presentation.home.homeamulet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.domain.repository.UserRepository
import com.byeboo.app.domain.repository.quest.QuestStateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeAmuletViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val questStateRepository: QuestStateRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeAmuletState())
    val uiState: StateFlow<HomeAmuletState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<HomeAmuletSideEffect>()
    val sideEffect: SharedFlow<HomeAmuletSideEffect> = _sideEffect.asSharedFlow()

    init {
        fetchJourneyFromLocal()
        fetchJourneyFromServer()
    }

    private fun fetchJourneyFromLocal() {
        viewModelScope.launch {
            val localJourney: String? = questStateRepository.getUserJourney()

            if (localJourney != null) {
                val amuletType = AmuletType.from(localJourney)

                _uiState.update {
                    it.copy(journey = amuletType)
                }
            }
        }
    }

    private fun fetchJourneyFromServer() {
        viewModelScope.launch {
            val result = userRepository.getUserJourney()
            if (result.isSuccess) {
                val data = result.getOrThrow()
                _uiState.update {
                    it.copy(
                        journey = AmuletType.from(data.journey),
                        journeyDescription = data.description
                    )
                }
            }
        }
    }

    fun navigateToHomeOnboarding() {
        viewModelScope.launch {
            _sideEffect.emit(HomeAmuletSideEffect.NavigateToHomeOnboarding)
        }
    }
}

