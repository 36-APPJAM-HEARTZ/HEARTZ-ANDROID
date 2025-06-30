package com.heartz.app.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.heartz.app.core.designsystem.type.ButtonType
import com.heartz.app.core.util.noRippleClickable

@Composable
fun ByeBooButton(
    text: String,
    onClick: () -> Unit,
    type: ButtonType = ButtonType.PRIMARY,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    verticalPadding: Dp = 16.dp
) {
    val backgroundColor = when {
        !enabled -> when (type) {
            ButtonType.PRIMARY -> Color.Gray
        }
        else -> when (type) {
            ButtonType.PRIMARY -> Color.Blue
        }
    }

    val textColor = when {
        !enabled -> when (type) {
            ButtonType.PRIMARY -> Color.DarkGray
        }
        else -> when (type) {
            ButtonType.PRIMARY -> Color.White
        }
    }

    Row(
        modifier = modifier
            .noRippleClickable { if (enabled) onClick() }
            .background(color = backgroundColor)
            .padding(
                horizontal = 24.dp,
                vertical = verticalPadding
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview
@Composable
private fun ByeBooButtonPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ByeBooButton(
                text = "다음으로",
                onClick = { }
            )
        }
    }
}
