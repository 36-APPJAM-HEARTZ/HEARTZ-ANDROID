package com.byeboo.app.presentation.quest.component.type

import androidx.annotation.DrawableRes
import com.byeboo.app.R

enum class QuestContentType(@DrawableRes val iconResId: Int) {
    THINKING(R.drawable.ic_think),
    QUEST_REASON(R.drawable.ic_tip_write),
    FEELING_CHANGE(R.drawable.ic_change)
}
