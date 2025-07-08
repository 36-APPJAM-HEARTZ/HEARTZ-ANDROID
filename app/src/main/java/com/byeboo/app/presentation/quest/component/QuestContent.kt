package com.byeboo.app.presentation.quest.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.component.contenttext.ContentText
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.presentation.quest.component.type.QuestContentType

@Composable
fun QuestContent(
    titleIcon: QuestContentType,
    titleText: String,
    contentText: String
) {
    val icon = ImageVector.vectorResource(id = titleIcon.iconResId)

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "title icon",
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = titleText,
                color = ByeBooTheme.colors.gray200,
                style = ByeBooTheme.typography.body2
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        ContentText(text = contentText)
    }
}

@Preview()
@Composable
private fun QuestContentPreview() {
    ByeBooTheme {
        QuestContent(
            titleIcon = QuestContentType.THINKING,
            titleText = "제목을 입력해주세요.",
            contentText = "내용을 입력해주세요."
        )
    }
}
