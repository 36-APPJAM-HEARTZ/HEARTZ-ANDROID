package com.byeboo.app.core.designsystem.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.byeboo.app.R

enum class LargeTagType(
    @DrawableRes val titleIcon: Int,
    @StringRes val titleResId: Int,
    val verticalPadding: Dp = 3.dp,
    val roundedCorner: Dp = 12.dp
) {
    EMOTION_NEUTRAL(
        titleIcon = R.drawable.img_neutral,
        titleResId = R.string.type_emotion_neutral
    ),

    EMOTION_SELF_AWARE(
        titleIcon = R.drawable.img_self_aware,
        titleResId = R.string.type_emotion_self_aware
    ),

    EMOTION_SADNESS(
        titleIcon = R.drawable.img_sadness,
        titleResId = R.string.type_emotion_sadness
    ),

    EMOTION_RELIEF(
        titleIcon = R.drawable.img_relief,
        titleResId = R.string.type_emotion_relief
    )
}
