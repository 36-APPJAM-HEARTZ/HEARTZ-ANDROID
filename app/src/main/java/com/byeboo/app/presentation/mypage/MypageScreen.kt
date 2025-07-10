package com.byeboo.app.presentation.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.component.button.ByeBooButton
import com.byeboo.app.core.designsystem.component.topbar.ByeBooTopBar
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@Composable
fun MyPageScreen(
    navigateToCompletedJourney: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val nickname by viewModel.nickname.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ByeBooTheme.colors.black),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ByeBooTopBar(
            modifier = Modifier
                .background(color = ByeBooTheme.colors.gray900Alpha80),
            title = "마이페이지"
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = ByeBooTheme.colors.whiteAlpha10)
                    .padding(horizontal = 24.dp, vertical = 18.dp)
            ) {
                Text(
                    text = nickname ?: "",
                    style = ByeBooTheme.typography.body3,
                    color = ByeBooTheme.colors.gray300
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                thickness = 1.dp,
                color = ByeBooTheme.colors.whiteAlpha10
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_tip_write),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "나의 기록",
                    style = ByeBooTheme.typography.body2,
                    color = ByeBooTheme.colors.gray200

                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            ByeBooButton(
                onClick = navigateToCompletedJourney,
                buttonText = "완료한 여정 돌아보기",
                buttonTextColor = ByeBooTheme.colors.gray50,
                buttonBackgroundColor = ByeBooTheme.colors.whiteAlpha10,
                buttonStrokeColor = ByeBooTheme.colors.primary300,
            )
        }
    }
}
