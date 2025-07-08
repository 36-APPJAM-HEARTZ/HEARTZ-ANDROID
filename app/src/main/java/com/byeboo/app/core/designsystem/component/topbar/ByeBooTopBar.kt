package com.byeboo.app.core.designsystem.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.noRippleClickable

@Composable
fun ByeBooTopBar(
    title: String? = null,
    onNavigateBack: (() -> Unit)? = null,
    onCloseClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(ByeBooTheme.colors.black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (onNavigateBack != null) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_left),
                    contentDescription = "뒤로가기",
                    tint = ByeBooTheme.colors.white,
                    modifier = Modifier
                        .size(24.dp)
                        .noRippleClickable(onNavigateBack)
                )
            } else {
                Spacer(modifier = Modifier.size(24.dp))
            }

            if (onCloseClick != null) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_cancel),
                    contentDescription = "닫기",
                    tint = ByeBooTheme.colors.white,
                    modifier = Modifier
                        .size(24.dp)
                        .noRippleClickable(onCloseClick)
                )
            } else {
                Spacer(modifier = Modifier.size(24.dp))
            }
        }

        title?.let {
            Text(
                text = it,
                style = ByeBooTheme.typography.sub1,
                color = ByeBooTheme.colors.white,
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFF000000)
fun PreviewByeBooTopBarNoTitle() {
    ByeBooTheme {
        ByeBooTopBar(
            title = "제목을 입력해주세요",
            onNavigateBack = {},
            onCloseClick = {}
        )
    }
}
