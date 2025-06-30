package com.byeboo.app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.core.state.UiState
import com.byeboo.app.domain.model.Dummy
import com.byeboo.app.domain.repository.DummyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dummyRepository: DummyRepository
) : ViewModel() {
    var uiState = MutableStateFlow(HomeState())
        private set

    fun getDummies(
        id: Int,
        email: String
    ) {
        viewModelScope.launch {
            dummyRepository.getDummies(
                request = Dummy(id = id, email = email)
            )
                .onSuccess { response ->
                    uiState.update {
                        it.copy(
                            user = UiState.Success(response)
                        )
                    }
                }
                .onFailure { e ->
                    uiState.update {
                        it.copy(user = UiState.Failure(e.message ?: "오류 발생"))
                    }
                }
        }
    }
}
