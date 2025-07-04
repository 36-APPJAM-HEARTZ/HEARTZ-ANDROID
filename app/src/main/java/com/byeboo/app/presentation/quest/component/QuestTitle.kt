package com.byeboo.app.presentation.quest.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.component.tag.SmallTag
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@Composable
fun QuestTitle(
    stepNumber: Int,
    createdAt: String,
    questQuestion: String

) {

    Column(
        modifier = Modifier
            .width(360.dp)
            .padding(horizontal = 24.dp, vertical = 9.5.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            SmallTag(tagText = "STEP ${stepNumber}")

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "${stepNumber}번째 퀘스트",
                color = ByeBooTheme.colors.gray400,
                style = ByeBooTheme.typography.body5
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = createdAt,
            color = ByeBooTheme.colors.gray400,
            style = ByeBooTheme.typography.body5
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = questQuestion,
            color = ByeBooTheme.colors.gray100,
            style = ByeBooTheme.typography.head1
        )
    }
}

@Preview
@Composable
private fun QuestTitlePreview() {

    ByeBooTheme {
        QuestTitle(
            stepNumber = 1,
            createdAt = "2025.07.04",
            questQuestion = "수박을 어떻게 하면 많이 먹을지 생각해보아요.")
    }

}