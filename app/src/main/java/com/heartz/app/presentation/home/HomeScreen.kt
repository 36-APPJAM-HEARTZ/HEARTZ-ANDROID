package com.heartz.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.heartz.app.core.state.UiState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (val state = uiState.user) {
            is UiState.Loading -> {
                Text(text = "로딩 중...", color = Color.Gray)
            }

            is UiState.Success -> {
                state.data.info.forEachIndexed { index, item ->
                    Text(text = "Info[$index]: $item")
                }
            }

            is UiState.Failure -> {
                Text(text = "에러: ${state.msg}", color = Color.Red)
            }

            UiState.Empty -> {
                Text(text = "데이터가 없습니다.")
            }
        }
    }
}
