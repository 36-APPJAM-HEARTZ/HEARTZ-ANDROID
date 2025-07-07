package com.byeboo.app.presentation.quest.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.component.chip.EmotionChip
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@Composable
fun QuestEmotionDescriptionCard(
    emotionType: LargeTagType
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = ByeBooTheme.colors.whiteAlpha10,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 24.dp, vertical = 18.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            EmotionChip(emotionType = emotionType, isSelected = true)

            Spacer(modifier = Modifier.width(24.dp))

            Text(
                text = getEmotionComment(emotionType),
                style = ByeBooTheme.typography.body5,
                color = ByeBooTheme.colors.gray300
            )
        }
    }
}

private fun getEmotionComment(emotionType: LargeTagType): String {
    return when (emotionType) {
        LargeTagType.EMOTION_NEUTRAL -> "마음이 가벼워졌다면 다행이에요. 당신은 지금 아주 건강하게 감정을 정리하고 있어요."
        LargeTagType.EMOTION_SELF_AWARE -> "마음이 가벼워졌다면"
        LargeTagType.EMOTION_SADNESS -> "마음"
        LargeTagType.EMOTION_RELIEF -> "당신은 지금 아주 건강하게 감정을 정리하고 있어요."
    }
}

@Preview
@Composable
private fun EmotionDescriptionCardPreview() {
    ByeBooTheme {
        QuestEmotionDescriptionCard(emotionType = LargeTagType.EMOTION_NEUTRAL)
    }
}
