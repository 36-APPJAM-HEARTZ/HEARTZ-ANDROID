package com.byeboo.app.presentation.quest.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.component.button.ByeBooButton
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.designsystem.ui.theme.gray900Alpha80

@Composable
fun QuitModal(
    stayButton: () -> Unit,
    quitButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(264.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = gray900Alpha80)
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "작성을 중단하시겠어요?",
                style = ByeBooTheme.typography.sub2,
                color = ByeBooTheme.colors.gray50
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "작성하시던 내용은\n저장되지 않아요.",
                style = ByeBooTheme.typography.body2,
                color = ByeBooTheme.colors.gray400,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ByeBooButton(
                    onClick = stayButton,
                    buttonText = "머무르기",
                    buttonTextColor = ByeBooTheme.colors.white,
                    buttonBackgroundColor = ByeBooTheme.colors.primary300,
                    modifier = Modifier.width(100.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                ByeBooButton(
                    onClick = quitButton,
                    buttonText = "나가기",
                    buttonTextColor = ByeBooTheme.colors.gray200,
                    buttonStrokeColor = ByeBooTheme.colors.gray400,
                    modifier = Modifier.width(100.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuitModalPreview() {
    ByeBooTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            QuitModal(
                stayButton = {},
                quitButton = {}
            )
        }
    }
}
