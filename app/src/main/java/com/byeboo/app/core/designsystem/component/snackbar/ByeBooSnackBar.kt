package com.byeboo.app.core.designsystem.component.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@Composable
fun ByeBooSnackBar(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(
                bottom = 16.dp,
                start = 24.dp,
                end = 24.dp
            )
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = ByeBooTheme.colors.warning300,
                shape = RoundedCornerShape(12.dp)
            )
            .background(ByeBooTheme.colors.warning50)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            textAlign = TextAlign.Center,
            style = ByeBooTheme.typography.body4,
            color = ByeBooTheme.colors.warning300
        )
    }
}