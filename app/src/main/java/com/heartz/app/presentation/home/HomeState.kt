package com.heartz.app.presentation.home

import com.heartz.app.core.state.UiState
import com.heartz.app.domain.model.DummyResultModel

data class HomeState(
    var user: UiState<DummyResultModel> = UiState.Loading
)
