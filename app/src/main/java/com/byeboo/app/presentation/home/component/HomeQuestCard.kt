package com.byeboo.app.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.noRippleClickable

@Composable
fun HomeQuestCard(
    title: String,
    subtitle: String,
    backgroundColor: Color = ByeBooTheme.colors.whiteAlpha10,
    shape: Shape = RoundedCornerShape(12.dp),
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor, shape)
            .border(1.dp, ByeBooTheme.colors.primary300, shape)
            .noRippleClickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = ByeBooTheme.typography.sub2,
                    color = ByeBooTheme.colors.gray50
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = subtitle,
                    style = ByeBooTheme.typography.body5,
                    color = ByeBooTheme.colors.gray300
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_right),
                contentDescription = null,
                tint = ByeBooTheme.colors.gray50
            )
        }
    }
}

@Preview()
@Composable
private fun ContentTextPreview() {
    ByeBooTheme {
        Column {
            HomeQuestCard(
                title = "오늘의 퀘스트 하러가기",
                subtitle = "퀘스트를 하고나면 한층 더 성장할 거에요."
            )
        }
    }
}
