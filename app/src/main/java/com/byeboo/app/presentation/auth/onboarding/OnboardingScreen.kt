package com.byeboo.app.presentation.auth.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.component.button.ByeBooButton
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.screenHeightDp
import com.byeboo.app.core.util.screenWidthDp

@Composable
fun OnboardingScreen(
    navigateToUserInfo: () -> Unit,
    padding: Dp,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val pageIndex by viewModel.pageIndex

    val contents = viewModel.currentContents()

    val pageSpace = if (pageIndex == 2) 24.dp else 16.dp

    val buttonText = if (pageIndex == 2) "시작하기" else "다음으로"

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.img_onboarding_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.padding(top = screenHeightDp(padding + 27.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.padding(horizontal = screenWidthDp(24.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = viewModel.showPageNumber(),
                    color = ByeBooTheme.colors.primary300,
                    style = ByeBooTheme.typography.body5
                )

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.clickable { viewModel.skipPage(navigateToUserInfo) },
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(
                        text = "Skip",
                        color = ByeBooTheme.colors.primary300,
                        style = ByeBooTheme.typography.body5.copy(
                            textDecoration = TextDecoration.Underline
                        )
                    )

                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_right),
                        contentDescription = "next",
                        tint = ByeBooTheme.colors.primary200,
                        modifier = Modifier.size(12.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = screenWidthDp(45.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                contents.forEach { content ->

                    Image(
                        painter = painterResource(id = content.imageRes),
                        contentDescription = "OnBoarding image",
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )

                    Spacer(modifier = Modifier.height(screenHeightDp(pageSpace)))

                    Text(
                        text = content.title,
                        color = ByeBooTheme.colors.primary900,
                        style = ByeBooTheme.typography.body3,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            ByeBooButton(
                modifier = Modifier
                    .padding(horizontal = screenWidthDp(24.dp))
                    .padding(bottom = padding),
                buttonText = buttonText,
                onClick = {
                    if (pageIndex == 2) {
                        navigateToUserInfo()
                    } else {
                        viewModel.nextPage()
                    }
                },
                buttonTextColor = ByeBooTheme.colors.white,
                buttonBackgroundColor = ByeBooTheme.colors.primary300
            )
        }
    }
}