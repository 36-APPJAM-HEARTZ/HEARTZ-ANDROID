package com.byeboo.app.presentation.home.homeamulet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.noRippleClickable
import com.byeboo.app.presentation.home.component.HomeAmuletCard

@Composable
fun HomeAmuletScreen(
    navigateToHomeOnboarding: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeAmuletViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isFlipped by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            if (effect is HomeAmuletSideEffect.NavigateToHomeOnboarding) {
                navigateToHomeOnboarding()
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.bg_userinfo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ByeBooTheme.colors.blackAlpha50)
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 35.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.height(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (!isFlipped) {
                        Text(
                            text = "카드를 뒤집어서 내용을 확인해주세요!",
                            style = ByeBooTheme.typography.body3,
                            color = ByeBooTheme.colors.whiteAlpha50,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 32.dp)
                        )
                    }
                }
                HomeAmuletCard(
                    frontImageRes = uiState.journey.frontImg,
                    backImageRes = uiState.journey.backImg,
                    description = uiState.journeyDescription,
                    isFlipped = isFlipped,
                    onFlip = { isFlipped = true },
                    modifier = Modifier.fillMaxWidth()
                )
                Box(
                    modifier = Modifier.height(80.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (isFlipped) {
                        Text(
                            text = "확인했어요",
                            style = ByeBooTheme.typography.body4,
                            color = ByeBooTheme.colors.secondary300,
                            textDecoration = TextDecoration.Underline,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp)
                                .noRippleClickable {
                                    viewModel.navigateToHomeOnboarding()
                                }
                        )
                    }
                }
            }
        }
    }
}