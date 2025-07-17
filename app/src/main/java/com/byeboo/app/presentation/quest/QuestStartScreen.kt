package com.byeboo.app.presentation.quest

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.component.button.ByeBooButton
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.noRippleClickable
import com.byeboo.app.core.util.screenHeightDp
import com.byeboo.app.core.util.screenWidthDp
import com.byeboo.app.presentation.auth.loading.LoadingViewModel
import com.byeboo.app.presentation.quest.component.GuideContent

@Composable
fun QuestStartScreen(
    navigateToQuest: () -> Unit,
    navigateToHome: () -> Unit,
    padding: Dp,
    modifier: Modifier = Modifier,
    viewModel: QuestStartViewModel = hiltViewModel(),
    loadingViewModel: LoadingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val nickname by loadingViewModel.nickname.collectAsStateWithLifecycle()


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
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = screenHeightDp(67.dp))
                .padding(horizontal = screenWidthDp(24.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_left),
                contentDescription = "뒤로가기",
                tint = ByeBooTheme.colors.white,
                modifier = Modifier
                    .size(24.dp)
                    .noRippleClickable{viewModel.onBackClick()}
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            GuideContent(
                userName = nickname.toString(),
                guideText = "님의 상황에 꼭 맞춘\n${uiState.journeyName} 여정의 퀘스트 30개를 드릴게요.\n\n제가 드리는 퀘스트와 함께\n이별을 극복해나가요!"
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        ByeBooButton(
            onClick = viewModel::onStartClick,
            buttonText = "시작하기",
            buttonTextColor = ByeBooTheme.colors.white,
            buttonBackgroundColor = ByeBooTheme.colors.primary300,
            modifier = Modifier
                .padding(horizontal = screenWidthDp(24.dp))
                .padding(bottom = padding)
        )

        Spacer(modifier = Modifier.height(screenHeightDp(8.dp)))
    }
}
