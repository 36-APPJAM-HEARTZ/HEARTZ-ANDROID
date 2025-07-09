package com.byeboo.app.core.designsystem.component.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@Composable
fun ContentText(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = ByeBooTheme.colors.whiteAlpha10,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 24.dp, vertical = 18.dp)

    ) {
        Text(
            text = text,
            style = ByeBooTheme.typography.body5,
            color = ByeBooTheme.colors.gray300
        )
    }
}

@Preview()
@Composable
private fun ContentTextPreview() {
    ByeBooTheme {
        ContentText("내용을 입력해주세요.")
    }
}
