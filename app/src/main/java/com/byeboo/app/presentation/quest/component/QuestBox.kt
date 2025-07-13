package com.byeboo.app.presentation.quest.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.byeboo.app.R
import com.byeboo.app.core.util.noRippleClickable
import com.byeboo.app.presentation.quest.QuestState

@Composable
fun QuestBox(
    questId: Long,
    questNumber: Int,
    state: QuestState,
    onQuestClick: (Long) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val isClickable = state is QuestState.Available || state is QuestState.Complete

    val clickableModifier = if (isClickable) {
        modifier.noRippleClickable { onQuestClick(questId) }
    } else {
        modifier
    }

    when (state) {
        is QuestState.Complete -> {
            CompleteContent(
                questNumber = questNumber,
                imageResId = R.drawable.quest_complete,
                modifier = clickableModifier
            )
        }

        is QuestState.Available -> {
            AvailableContent(
                questNumber = questNumber,
                imageResId = R.drawable.quest_available,
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
