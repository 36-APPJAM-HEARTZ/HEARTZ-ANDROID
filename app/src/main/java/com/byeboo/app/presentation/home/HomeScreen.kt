package com.byeboo.app.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    // TODO: home animation 
//    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
//    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bori_home_7))
    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets(0))
            .background(ByeBooTheme.colors.black)
    ) {
        /// TODO: home animation 
//        LottieAnimation(
//            composition = composition,
//            iterations = LottieConstants.IterateForever,
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop,
//            renderMode = RenderMode.HARDWARE,
//            enableMergePaths = true
//        )
    }
}
