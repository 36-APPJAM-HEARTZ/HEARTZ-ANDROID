package com.byeboo.app.core.designsystem.component.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.byeboo.app.core.designsystem.type.MiddleTagType
import com.byeboo.app.core.designsystem.type.TagColorType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@Composable
fun MiddleTag(
    middleTagType: MiddleTagType,
    text: String,
    modifier: Modifier = Modifier
) {
    val tagText = if (middleTagType.dynamicContent) {
        stringResource(middleTagType.titleResId, text)
    } else {
        stringResource(middleTagType.titleResId)
    }

    Box(
        modifier = modifier
            .background(
                color = middleTagColor(middleTagType.backgroundColor),
                shape = RoundedCornerShape(middleTagType.roundedCorner)
            )
            .padding(
                horizontal = middleTagType.horizontalPadding,
                vertical = middleTagType.verticalPadding
            )
    ) {
        Text(
            text = tagText,
            color = middleTagColor(middleTagType.textColor),
            style = ByeBooTheme.typography.cap2
        )
    }
}

@Composable
fun middleTagColor(colorType: TagColorType): Color {
    return when (colorType) {
        TagColorType.WHITE_ALPHA_10 -> ByeBooTheme.colors.whiteAlpha10
        TagColorType.SECONDARY_ALPHA_30 -> ByeBooTheme.colors.secondary300Alpha30
        TagColorType.GRAY_300 -> ByeBooTheme.colors.gray300
        TagColorType.SECONDARY_300 -> ByeBooTheme.colors.secondary300
        TagColorType.PRIMARY_50 -> ByeBooTheme.colors.primary50
        TagColorType.PRIMARY_300 -> ByeBooTheme.colors.primary300
    }
}
