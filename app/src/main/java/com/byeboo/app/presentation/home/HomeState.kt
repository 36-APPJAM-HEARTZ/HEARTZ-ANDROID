package com.byeboo.app.presentation.home

import com.byeboo.app.core.state.UiState
import com.byeboo.app.domain.model.DummyResultModel

data class HomeState(
    var user: UiState<DummyResultModel> = UiState.Loading
)
