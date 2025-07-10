package com.byeboo.app.presentation.auth.userinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.component.button.ByeBooActivationButton
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.noRippleClickable
import com.byeboo.app.domain.model.NicknameValidationResult
import com.byeboo.app.presentation.auth.userinfo.component.StepProgressBar
import com.byeboo.app.presentation.auth.userinfo.model.toValidationState
import com.byeboo.app.presentation.auth.userinfo.screen.UserInfoEmotionScreen
import com.byeboo.app.presentation.auth.userinfo.screen.UserInfoNicknameScreen
import com.byeboo.app.presentation.auth.userinfo.screen.UserInfoQuestScreen
import kotlinx.coroutines.launch

@Composable
fun UserInfoScreen(
    navigateToOnboarding: () -> Unit,
    navigateToLoading: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UserInfoViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 3 })

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val coroutineScope = rememberCoroutineScope()

    val isStepValid by remember(
        pagerState.currentPage,
        uiState.nicknameValidation,
        uiState.selectedEmotion,
        uiState.selectedQuest
    ) {
        derivedStateOf {
            when (pagerState.currentPage) {
                0 -> uiState.nicknameValidation == NicknameValidationResult.Valid
                1 -> uiState.selectedEmotion != null
                2 -> uiState.selectedQuest != null
                else -> false
            }
        }
    }

    var previousPage by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is UserInfoSideEffect.NavigateToLoading -> navigateToLoading()
            }
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.bg_userinfo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.padding(top = 72.dp))

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_left),
                contentDescription = "뒤로가기",
                tint = ByeBooTheme.colors.white,
                modifier = Modifier
                    .size(24.dp)
                    .noRippleClickable {
                        coroutineScope.launch {
                            if (pagerState.currentPage == 0) {
                                navigateToOnboarding()
                            } else {
                                val targetPage = pagerState.currentPage - 1
                                previousPage = pagerState.currentPage
                                pagerState.scrollToPage(targetPage)
                            }
                        }
                    }
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))

            StepProgressBar(currentStep = pagerState.currentPage + 1)

            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                when (page) {
                    0 -> UserInfoNicknameScreen(
                        nickname = uiState.nickname,
                        validationState = uiState.nicknameValidation.toValidationState(),
                        onTextChange = viewModel::updateNickname
                    )

                    1 -> UserInfoEmotionScreen(
                        selectedEmotion = uiState.selectedEmotion,
                        onEmotionSelect = viewModel::updateEmotion
                    )

                    2 -> UserInfoQuestScreen(
                        selectedQuest = uiState.selectedQuest,
                        onQuestSelect = viewModel::updateQuest
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))

            ByeBooActivationButton(
                buttonDisableColor = ByeBooTheme.colors.blackAlpha50,
                buttonDisableTextColor = ByeBooTheme.colors.gray400,
                isEnabled = isStepValid,
                buttonText = "다음으로",
                onClick = {
                    coroutineScope.launch {
                        val nextPage = pagerState.currentPage + 1

                        when (pagerState.currentPage) {
                            0 -> {
                                if (previousPage > 0) {
                                    viewModel.resetEmotion()
                                    viewModel.resetQuest()
                                }
                            }

                            1 -> {
                                if (previousPage > 1) {
                                    viewModel.resetQuest()
                                }
                            }
                        }

                        previousPage = pagerState.currentPage

                        if (pagerState.currentPage < 2) {
                            pagerState.scrollToPage(nextPage)
                        } else {
                            viewModel.finishUserInfo()
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.padding(bottom = 56.dp))
        }
    }
}
