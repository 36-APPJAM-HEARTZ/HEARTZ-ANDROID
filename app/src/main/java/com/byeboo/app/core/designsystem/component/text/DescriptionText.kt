package com.byeboo.app.core.designsystem.component.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@Composable
fun DescriptionText(
    nicknameText: String? = null,
    title: String,
    guideText: String,
    contentText: String,
    modifier: Modifier = Modifier,
    top: Dp = 0.dp,
    bottom: Dp = 0.dp,
    start: Dp = 0.dp,
    end: Dp = 0.dp
) {
    Column(modifier = modifier.padding(top = top, bottom = bottom, start = start, end = end)) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = ByeBooTheme.colors.gray50)) {
                    append(nicknameText ?: "")
                }
                append("\n")
                withStyle(style = SpanStyle(color = ByeBooTheme.colors.primary300)) {
                    append(title)
                }
                withStyle(style = SpanStyle(color = ByeBooTheme.colors.gray50)) {
                    append(guideText)
                }
            },
            style = ByeBooTheme.typography.head1
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Text(
            text = contentText,
            style = ByeBooTheme.typography.body5,
            color = ByeBooTheme.colors.gray400
        )
    }
}
