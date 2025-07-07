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
import com.byeboo.app.presentation.auth.userinfo.component.NicknameText
import com.byeboo.app.presentation.auth.userinfo.component.UserInfoQuestCard
import com.byeboo.app.presentation.auth.userinfo.model.QuestItem
import kotlinx.collections.immutable.persistentListOf

@Composable
fun UserInfoQuestScreen(
    selectedQuest: String?,
    onQuestSelect: (String) -> Unit
) {
    val quests = persistentListOf(
        QuestItem(
            title = "질문에 답하기",
            description = "질문을 통해\n상황과 감정을\n정리해요",
            imageResId = R.drawable.ic_book
        ),
        QuestItem(
            title = "활동 인증하기",
            description = "작은 미션을 통해\n몸과 마음을\n가볍게 해요",
            imageResId = R.drawable.ic_shoes
        )
    )
    Column {
        NicknameText(
            title = "퀘스트 방식",
            guideText = "을 골라주세요",
            contentText = "나에게 맞는 방식으로 퀘스트를 받아볼 수 있어요."
        )
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            quests.forEach { quest ->
                val onCardClick = remember(quest.title) {
                    { onQuestSelect(quest.title) }
                }

                UserInfoQuestCard(
                    title = quest.title,
                    content = quest.description,
                    imageRes = quest.imageResId,
                    isSelected = selectedQuest == quest.title,
                    onCardClick = onCardClick,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }

}