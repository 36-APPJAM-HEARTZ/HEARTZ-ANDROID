package com.byeboo.app.presentation.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.core.designsystem.component.button.ByeBooActivationButton
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@Composable
fun MypageScreen(
    modifier: Modifier = Modifier,
    viewModel: MypageViewModel = hiltViewModel()
) {
    val nickname by viewModel.nickname.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = nickname ?: "하츠",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(40.dp))

        ByeBooActivationButton(
            buttonDisableColor = ByeBooTheme.colors.whiteAlpha10,
            buttonText = "테스트",
            onClick = {
                viewModel.logout()
            },
            buttonDisableTextColor = ByeBooTheme.colors.gray300,
            isEnabled = true,
        )
    }
}
