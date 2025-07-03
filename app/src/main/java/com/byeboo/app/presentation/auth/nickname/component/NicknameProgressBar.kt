package com.byeboo.app.presentation.auth.nickname.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.designsystem.ui.theme.primary300
import com.byeboo.app.core.designsystem.ui.theme.primary300Alpha20

@Composable
fun StepProgressBar(
    currentStep: Int,
    modifier: Modifier = Modifier,
    activeColor: Color = primary300,
    inactiveColor: Color = primary300Alpha20
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier
    ) {
        repeat(3) { index ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(6.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (index == currentStep - 1) activeColor else inactiveColor)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun StepProgressBarPreview() {
    ByeBooTheme {
        Column(modifier = Modifier.padding(24.dp)) {
            StepProgressBar(
                currentStep = 1
            )
            Spacer(Modifier.padding(20.dp))
            StepProgressBar(
                currentStep = 2
            )
            Spacer(Modifier.padding(20.dp))
            StepProgressBar(
                currentStep = 3
            )
        }
    }
}
