package com.byeboo.app.presentation.quest.component.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.screenHeightDp
import com.byeboo.app.core.util.screenWidthDp

@Composable
fun ByeBooDragHandle(
    content: @Composable () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = screenHeightDp(24.dp), bottom = screenHeightDp(33.dp))
            .clip(shape = RoundedCornerShape(12.dp))
    ) {
        Box(
            modifier = Modifier
                .width(screenWidthDp(70.dp))
                .height(screenHeightDp(4.dp))
                .background(ByeBooTheme.colors.white)
        )
        content()
    }
}
