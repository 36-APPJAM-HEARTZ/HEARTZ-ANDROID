package com.heartz.app.core.designsystem.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun HeartzTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = HeartzColorScheme,
        typography = Typography,
        content = content
    )
}
