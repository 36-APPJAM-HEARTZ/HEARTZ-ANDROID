package com.byeboo.app.core.designsystem.component.chip

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.component.tag.LargeTag
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.noRippleClickable

@Composable
fun EmotionChip(
    modifier: Modifier = Modifier,
    emotionType: LargeTagType,
    isSelected: Boolean = false,
    onChipClick: ((LargeTagType) -> Unit)? = null
) {
    val backgroundColor = if (isSelected) {
        ByeBooTheme.colors.primary300
    } else {
        ByeBooTheme.colors.whiteAlpha10
    }

    Column(
        modifier = modifier
            .then(
                if (onChipClick != null) {
                    Modifier.noRippleClickable(onClick = { onChipClick(emotionType) })
                } else {
                    Modifier
                }
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(emotionType.titleIcon),
            contentDescription = null,
            modifier = Modifier
                .width(85.dp)
                .height(84.dp)
        )

        Spacer(modifier = modifier.height(8.dp))

        LargeTag(
            largeTagType = emotionType,
            backgroundColor = backgroundColor
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun EmotionChipPreview() {
    ByeBooTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            EmotionChip(emotionType = LargeTagType.EMOTION_NEUTRAL, isSelected = true) { }

            EmotionChip(emotionType = LargeTagType.EMOTION_SADNESS) { }
        }
    }
}
