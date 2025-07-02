package com.byeboo.app.presentation.quest.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.noRippleClickable
import com.byeboo.app.presentation.quest.QuestState

@Composable
fun QuestBox(
    questNumber: Int,
    state: QuestState,
    onQuestClick: (Int) -> Unit = {},
    imageResId: Int = R.drawable.ic_launcher_background,
    modifier: Modifier = Modifier
) {
    val isClickable = state is QuestState.Available || state is QuestState.InProgress

    val clickableModifier = if (isClickable) {
        modifier.noRippleClickable { onQuestClick(questNumber) }
    } else {
        modifier
    }

    when (state) {
        is QuestState.InProgress -> {
            InProgressContent(
                questNumber = questNumber,
                imageResId = imageResId,
                modifier = clickableModifier
            )
        }

        is QuestState.Available -> {
            AvailableContent(
                questNumber = questNumber,
                imageResId = imageResId,
                modifier = clickableModifier
            )
        }

        is QuestState.TimerLocked -> {
            TimerLockedContent(
                questNumber = questNumber,
                remainingTime = state.remainTime,
                modifier = clickableModifier
            )
        }

        is QuestState.Locked -> {
            LockedContent(
                questNumber = questNumber,
                modifier = clickableModifier
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun ImprovedQuestPreview() {
    ByeBooTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            QuestBox(1, QuestState.InProgress, imageResId = R.drawable.ic_launcher_background)
            QuestBox(2, QuestState.Available, imageResId = R.drawable.ic_launcher_background)
            QuestBox(3, QuestState.TimerLocked("23:59"))
            QuestBox(4, QuestState.Locked)
        }
    }
}
