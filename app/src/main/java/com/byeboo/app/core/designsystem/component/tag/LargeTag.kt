package com.byeboo.app.core.designsystem.component.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@Composable
fun LargeTag(
    largeTagType: LargeTagType,
    modifier: Modifier = Modifier,
    backgroundColor: Color
) {
    Box(
        modifier = modifier
            .width(85.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(largeTagType.roundedCorner)
            )
            .padding(
                vertical = largeTagType.verticalPadding
            ),
        contentAlignment = Alignment.Center

    ) {
        Text(
            text = stringResource(largeTagType.titleResId),
            color = ByeBooTheme.colors.gray50,
            style = ByeBooTheme.typography.body4
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun LargeTagPreview() {
    ByeBooTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()

        ) {
            LargeTag(
                largeTagType = LargeTagType.EMOTION_NEUTRAL,
                backgroundColor = ByeBooTheme.colors.primary300
            )

            LargeTag(
                largeTagType = LargeTagType.EMOTION_SELF_AWARE,
                backgroundColor = ByeBooTheme.colors.whiteAlpha10
            )

            LargeTag(
                largeTagType = LargeTagType.EMOTION_SADNESS,
                backgroundColor = ByeBooTheme.colors.whiteAlpha10
            )

            LargeTag(
                largeTagType = LargeTagType.EMOTION_RELIEF,
                backgroundColor = ByeBooTheme.colors.whiteAlpha10
            )
        }
    }
}
