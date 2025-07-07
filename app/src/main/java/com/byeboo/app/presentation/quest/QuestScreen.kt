package com.byeboo.app.presentation.quest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.byeboo.app.presentation.quest.component.bottomsheet.ByeBooBottomSheet
import com.byeboo.app.presentation.quest.component.bottomsheet.ByeBooDragHandle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "quest Screen",
            color = Color.Black
        )

        ByeBooBottomSheet(
            onDismiss = {},
            onEmotionSelected = {},
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            dragHandle = { ByeBooDragHandle() },
            isSelected = false
        )
    }
}
