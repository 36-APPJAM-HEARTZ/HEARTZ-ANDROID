package com.byeboo.app.core.designsystem.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun ByeBooButton(
    onClick: () -> Unit,
    buttonText: String,
    buttonTextColor: Color,
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color = Color.Unspecified,
    buttonStrokeColor: Color = Color.Unspecified
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(color = buttonBackgroundColor)
            .border(width = 1.dp, color = buttonStrokeColor, shape = RoundedCornerShape(12.dp))
            .noRippleClickable(onClick = onClick)
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = buttonText,
            style = ByeBooTheme.typography.body1,
            color = buttonTextColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ByeBooButtonPreview2() {
    ByeBooTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            ByeBooButton(
                onClick = {},
                buttonText = "버튼",
                buttonTextColor = ByeBooTheme.colors.white,
                buttonBackgroundColor = ByeBooTheme.colors.primary300,
                modifier = Modifier.width(76.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            ByeBooButton(
                onClick = {},
                buttonText = "버튼",
                buttonTextColor = ByeBooTheme.colors.gray200,
                buttonStrokeColor = ByeBooTheme.colors.gray400,
                modifier = Modifier.width(76.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            ByeBooButton(
                onClick = {},
                buttonText = "버튼",
                buttonTextColor = ByeBooTheme.colors.white,
                buttonBackgroundColor = ByeBooTheme.colors.primary300
            )
        }
    }
}
