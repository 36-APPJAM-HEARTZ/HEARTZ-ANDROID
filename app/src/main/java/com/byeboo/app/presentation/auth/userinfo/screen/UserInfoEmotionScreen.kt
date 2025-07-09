package com.byeboo.app.presentation.auth.userinfo.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.component.text.DesCriptionText
import com.byeboo.app.presentation.auth.userinfo.component.UserInfoEmotionCard
import com.byeboo.app.presentation.auth.userinfo.model.EmotionItem
import kotlinx.collections.immutable.persistentListOf

@Composable
fun UserInfoEmotionScreen(
    selectedEmotion: String?,
    onEmotionSelect: (String) -> Unit
) {
    val emotions = persistentListOf(
        EmotionItem(
            title = "너무 힘들어요",
            imageResId = R.drawable.ic_emotion_sad
        ),
        EmotionItem(
            title = "극복 중이에요",
            imageResId = R.drawable.ic_emotion_soso
        ),
        EmotionItem(
            title = "꽤 극복했어요",
            imageResId = R.drawable.ic_emotion_good
        )
    )
    Column {
        DesCriptionText(
            title = "감정 상태",
            guideText = "를 알려주세요",
            contentText = "이별 후, 어떤 감정으로 하루를 보내고 계신가요?"
        )
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            emotions.forEach { emotion ->
                val onCardClick = remember(emotion.title) {
                    { onEmotionSelect(emotion.title) }
                }
                UserInfoEmotionCard(
                    content = emotion.title,
                    imageRes = emotion.imageResId,
                    isSelected = selectedEmotion == emotion.title,
                    onCardClick = onCardClick,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
