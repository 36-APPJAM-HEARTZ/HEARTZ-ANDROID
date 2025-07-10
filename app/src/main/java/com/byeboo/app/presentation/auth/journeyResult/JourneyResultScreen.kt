package com.byeboo.app.presentation.auth.journeyResult

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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

@Composable
fun JourneyResultScreen(
    modifier: Modifier = Modifier,
    viewModel: JourneyResultViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val nickname by viewModel.nickname.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 67.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.bg_userinfo),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "지금 ${nickname ?: ""} 님에게 필요한 건",
                style = ByeBooTheme.typography.body1,
                color = ByeBooTheme.colors.white,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(31.dp))

            Image(
                painter = painterResource(id = uiState.journey.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(horizontal = 48.dp)

            )

            Spacer(modifier = Modifier.height(31.dp))

            Text(
                text = "${uiState.journeyDescription}",
                style = ByeBooTheme.typography.body5,
                color = ByeBooTheme.colors.secondary100,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(31.dp))

            Text(
                text = "확인했어요",
                style = ByeBooTheme.typography.body4,
                color = ByeBooTheme.colors.secondary300,
                textDecoration = TextDecoration.Underline,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
