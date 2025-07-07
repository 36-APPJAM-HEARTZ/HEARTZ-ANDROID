package com.byeboo.app.core.designsystem.component.line

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.designsystem.ui.theme.whiteAlpha10

@Composable
fun ByeBooLine(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 312.dp, height = 1.dp)
            .background(color = whiteAlpha10)
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun ByeBooLinePreview() {
    ByeBooTheme {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            ByeBooLine(
                modifier = Modifier.padding(vertical = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}
