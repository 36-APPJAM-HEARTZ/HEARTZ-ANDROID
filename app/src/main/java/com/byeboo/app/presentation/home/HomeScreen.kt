package com.byeboo.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.presentation.home.component.HomeProgressCard
import com.byeboo.app.presentation.home.component.HomeQuestCard
import com.byeboo.app.presentation.home.component.HomeTextCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val nickname by viewModel.nickname.collectAsStateWithLifecycle()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bori_home))
    val displayName = nickname ?: "하츠핑"
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ByeBooTheme.colors.black)
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            renderMode = RenderMode.AUTOMATIC,
            enableMergePaths = true
        )
        if (composition != null) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp)
                    .padding(top = 67.dp)
            )
            {
                HomeQuestCard(
                    title = "오늘의 퀘스트 하러가기",
                    subtitle = "퀘스트를 하고나면 한층 더 성장할 거에요."
                )
                Spacer(modifier = Modifier.height(16.dp))

                HomeProgressCard(
                    title = displayName + "님의 자기 성찰 여정",
                    currentStep = 15,
                    totalSteps = 30
                )
                Spacer(modifier = Modifier.height(16.dp))

                HomeTextCard(
                    title = "천천히, 하지만 분명하게. 오늘도 나아가 봐요."
                )
            }
        }
    }
}
