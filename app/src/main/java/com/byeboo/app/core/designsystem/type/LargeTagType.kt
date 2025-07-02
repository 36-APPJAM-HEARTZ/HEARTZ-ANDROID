package com.byeboo.app.core.designsystem.type

import androidx.annotation.StringRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.byeboo.app.R

enum class LargeTagType (
    @StringRes val titleResId: Int,
    val horizontalPadding: Dp = 17.5.dp,
    val verticalPadding: Dp = 3.dp,
    val roundedCorner: Dp = 12.dp
) {
    EMOTION_NEUTRAL(
        titleResId = R.string.type_emotion_neutral
    ),

    EMOTION_SELF_AWARE(
        titleResId = R.string.type_emotion_self_aware
    ),

    EMOTION_SADNESS(
        titleResId = R.string.type_emotion_sadness
    ),

    EMOTION_RELIEF(
        titleResId = R.string.type_emotion_relief
    )


}

