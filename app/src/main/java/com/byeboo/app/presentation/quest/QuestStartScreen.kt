package com.byeboo.app.presentation.quest

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.core.designsystem.component.button.ByeBooButton
import com.byeboo.app.core.designsystem.component.topbar.ByeBooTopBar
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.presentation.quest.component.GuideContent

@Composable
fun QuestStartScreen(
    navigateToQuest: () -> Unit,
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QuestStartViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is QuestStartSideEffect.NavigateToQuest -> navigateToQuest()
                is QuestStartSideEffect.NavigateToHome -> navigateToHome()
            }
        }
    }

    BackHandler { viewModel.onBackClick() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = ByeBooTheme.colors.black)
    ) {
        ByeBooTopBar(
            onNavigateBack = viewModel::onBackClick,
            modifier = Modifier.background(color = ByeBooTheme.colors.black)
        )

        Spacer(modifier = Modifier.height(11.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            GuideContent(
                userName = uiState.nickname,
                guideText = "님의 상황에 꼭 맞춘\n${uiState.journeyName} 여정의 퀘스트 30개를 드릴게요.\n\n제가 드리는 퀘스트와 함꼐\n이별을 극복해나가요!"
            )

            Spacer(modifier = Modifier.weight(1f))

            ByeBooButton(
                onClick = viewModel::onStartClick,
                buttonText = "시작하기",
                buttonTextColor = ByeBooTheme.colors.white,
                buttonBackgroundColor = ByeBooTheme.colors.primary300,
                modifier = Modifier
            )

            Spacer(modifier = Modifier.padding(bottom = 56.dp))
        }
    }
}
