package com.byeboo.app.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.screenHeightDp
import com.byeboo.app.core.util.screenWidthDp

@Composable
fun HomeTextCard(
    title: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                ByeBooTheme.colors.primary50,
                RoundedCornerShape(12.dp)
            )
            .padding(horizontal = screenWidthDp(24.dp), vertical = screenHeightDp(9.dp))
    ) {
        Text(
            text = title,
            style = ByeBooTheme.typography.body5,
            color = ByeBooTheme.colors.primary400,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun HomeProgressCardPreview() {
    ByeBooTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            HomeTextCard(
                title = "제가 하츠핑님의 이별 극복을 도와드릴게요."
            )
        }
    }
}
