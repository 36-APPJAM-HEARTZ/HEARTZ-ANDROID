package com.byeboo.app.presentation.auth.onboarding

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.core.designsystem.component.button.ByeBooActivationButton
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@Composable
fun OnboardingScreen(
    navigateToUserInfo: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            ByeBooActivationButton(
                buttonDisableColor = ByeBooTheme.colors.blackAlpha50,
                buttonText = "다음으로",
                buttonDisableTextColor = ByeBooTheme.colors.gray400,
                onClick = {
                    viewModel.saveNicknameAndNavigate(navigateToUserInfo)
                },
                isEnabled = uiState.nickname.isNotBlank()
            )
        },
        modifier = modifier
    ) { paddingValues ->
        OutlinedTextField(
            value = uiState.nickname,
            onValueChange = viewModel::updateNickname,
            placeholder = { Text("닉네임을 입력해주세요") },
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
                .padding(16.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (uiState.nickname.trim().isNotBlank()) {
                        viewModel.saveNicknameAndNavigate(navigateToUserInfo)
                    }
                }
            )
        )
    }
}
