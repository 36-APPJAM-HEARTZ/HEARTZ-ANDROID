package com.byeboo.app.presentation.auth.loading

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.screenHeightDp

@Composable
fun LoadingScreen(
    navigateToHomeAmulet: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoadingViewModel = hiltViewModel()
) {
    val nickname by viewModel.nickname.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                LoadingSideEffect.NavigateToHomeAmulet -> navigateToHomeAmulet()
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ByeBooTheme.colors.black),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .width(screenHeightDp(110.dp))
                .height(screenHeightDp(110.dp))
                .graphicsLayer(
                    scaleX = 4f,
                    scaleY = 4f
                )
        ) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.loading_byeboo)
            )
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever
            )
        }
        Text(
            buildAnnotatedString {
                withStyle(
                    style = ByeBooTheme.typography.body1.toSpanStyle()
                        .copy(color = ByeBooTheme.colors.primary300)
                ) {
                    append(nickname)
                }
                withStyle(
                    style = ByeBooTheme.typography.body3.toSpanStyle()
                        .copy(color = ByeBooTheme.colors.gray50)
                ) {
                    append("님에게 꼭 맞는\n이별 극복 여정을 찾는 중...")
                }
            },
            style = ByeBooTheme.typography.body3,
            textAlign = TextAlign.Center
        )
    }
}
