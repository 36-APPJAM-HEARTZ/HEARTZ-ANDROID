package com.byeboo.app.presentation.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.screenHeightDp
import com.byeboo.app.core.util.screenWidthDp

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
            .background(ByeBooTheme.colors.black)
            .padding(horizontal = screenWidthDp(24.dp))
            .padding(top = screenHeightDp(67.dp)),
    ) {
        Text(
            text = "마이페이지",
            style = ByeBooTheme.typography.sub1,
            color = ByeBooTheme.colors.white,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(screenHeightDp(24.dp)))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = ByeBooTheme.colors.whiteAlpha10)
                .padding(horizontal = screenWidthDp(24.dp), vertical = screenHeightDp(18.dp))
        ) {
            Text(
                text = nickname ?: "",
                style = ByeBooTheme.typography.body3,
                color = ByeBooTheme.colors.gray300
            )
        }

        Spacer(modifier = Modifier.height(screenHeightDp(8.dp)))

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = screenHeightDp(8.dp)),
            thickness = 1.dp,
            color = ByeBooTheme.colors.whiteAlpha10
        )

        Spacer(modifier = Modifier.height(screenHeightDp(24.dp)))

        Row() {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_tip_write),
                contentDescription = null,
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(screenWidthDp(8.dp)))

            Text(
                text = "나의 기록",
                style = ByeBooTheme.typography.body2,
                color = ByeBooTheme.colors.gray200

            )
        }

        Spacer(modifier = Modifier.height(screenHeightDp(12.dp)))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = ByeBooTheme.colors.whiteAlpha10)
                .border(width = 1.dp, color = ByeBooTheme.colors.primary300, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = screenWidthDp(24.dp), vertical = screenHeightDp(21.dp))
        ) {
            Text(
                text = "완료한 여정 돌아보기",
                style = ByeBooTheme.typography.body2,
                color = ByeBooTheme.colors.gray50
            )
        }
    }
}
