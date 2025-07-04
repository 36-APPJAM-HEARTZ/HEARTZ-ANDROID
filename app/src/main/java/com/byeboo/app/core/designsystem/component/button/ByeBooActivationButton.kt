package com.byeboo.app.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.noRippleClickable

@Composable
fun ByeBooActivationButton(
    buttonDisableColor: Color,
    buttonText: String,
    buttonDisableTextColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = false
) {
    val buttonBackgroundColor = if (isEnabled) ByeBooTheme.colors.primary300 else buttonDisableColor
    val buttonTextStyle = if (isEnabled) ByeBooTheme.typography.body1 else ByeBooTheme.typography.body2
    val buttonTextColor = if (isEnabled) ByeBooTheme.colors.white else buttonDisableTextColor

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(color = buttonBackgroundColor)
            .noRippleClickable(onClick = { if (isEnabled) onClick })
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buttonText,
            style = buttonTextStyle,
            color = buttonTextColor
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0x1A000000)
@Composable
fun ByeBooActivationButtonPreview() {
    ByeBooTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = (25.5).dp)
        ) {
            ByeBooActivationButton(
                buttonDisableColor = ByeBooTheme.colors.blackAlpha50,
                buttonText = "다음으로",
                buttonDisableTextColor = ByeBooTheme.colors.gray400,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(20.dp))

            ByeBooActivationButton(
                buttonDisableColor = ByeBooTheme.colors.blackAlpha50,
                buttonText = "다음으로",
                buttonDisableTextColor = ByeBooTheme.colors.gray400,
                onClick = {},
                isEnabled = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            ByeBooActivationButton(
                buttonDisableColor = ByeBooTheme.colors.whiteAlpha10,
                buttonText = "완료하기",
                buttonDisableTextColor = ByeBooTheme.colors.gray300,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(20.dp))

            ByeBooActivationButton(
                buttonDisableColor = ByeBooTheme.colors.whiteAlpha10,
                buttonText = "완료하기",
                buttonDisableTextColor = ByeBooTheme.colors.gray300,
                onClick = {},
                isEnabled = true
            )
        }
    }
}