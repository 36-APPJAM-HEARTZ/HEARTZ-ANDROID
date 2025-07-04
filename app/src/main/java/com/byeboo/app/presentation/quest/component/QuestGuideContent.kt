package com.byeboo.app.presentation.quest.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@Composable
fun GuideContent(
    userName: String,
    guideText: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 49.dp, vertical = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = ByeBooTheme.colors.primary300)) {
                    append("QUEST JOURNEY")
                }

                append("\n")

                withStyle(style = SpanStyle(color = ByeBooTheme.colors.gray400)) {
                    append("START!")
                }
            },
            style = ByeBooTheme.typography.head1,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        Image(
            painter = painterResource(id = R.drawable.bori_clover),
            contentDescription = "이미지",
            modifier = Modifier.size(width = 262.dp, height = 259.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = ByeBooTheme.colors.primary300)) {
                    append(userName)
                }
                withStyle(style = SpanStyle(color = ByeBooTheme.colors.gray300)) {
                    append(guideText)
                }
            },
            style = ByeBooTheme.typography.body3,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun GuideContentPreview() {
    ByeBooTheme {
        GuideContent(
            userName = "하츠핑",
            guideText = "님의 상황에 꼭 맞춘\n자기 성찰 여정의 퀘스트 30개를 드릴게요.\n\n제가 드리는 퀘스트와 함꼐\n이별을 극복해나가요!"
        )
    }
}