package com.byeboo.app.presentation.quest.record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.component.tag.SmallTag
import com.byeboo.app.core.designsystem.component.topbar.ByeBooTopBar
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.presentation.quest.component.QuestCompleteCard
import com.byeboo.app.presentation.quest.component.QuestContent
import com.byeboo.app.presentation.quest.component.QuestEmotionDescriptionCard
import com.byeboo.app.presentation.quest.component.type.QuestContentType
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun QuestRecordingCompleteScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QuestRecordingCompleteViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    val emotionType = when (uiState.questEmotionState) {
        "자기이해" -> LargeTagType.EMOTION_SELF_AWARE
        "그저그런" -> LargeTagType.EMOTION_RELIEF
        "슬픔" -> LargeTagType.EMOTION_SADNESS
        else -> LargeTagType.EMOTION_RELIEF
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = ByeBooTheme.colors.gray900Alpha80)
    ) {
        ByeBooTopBar(
            modifier = Modifier.background(color = ByeBooTheme.colors.gray900Alpha80),
            onNavigateBack = onNavigateBack
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            QuestCompleteCard()

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SmallTag(tagText = "STEP ${uiState.stepNumber}")

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${uiState.questNumber}번째 퀘스트",
                    style = ByeBooTheme.typography.body2,
                    color = ByeBooTheme.colors.gray500
                )

                Spacer(modifier = Modifier.height(12.dp))

                CreatedText(uiState.createdAt)

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = uiState.questTitle,
                    style = ByeBooTheme.typography.head1,
                    color = ByeBooTheme.colors.gray100,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                QuestContent(
                    titleIcon = QuestContentType.THINKING,
                    titleText = "이렇게 생각했어요",
                    contentText = uiState.questAnswer
                )

                Spacer(modifier = Modifier.height(48.dp))

                QuestEmotionDescriptionContent(
                    questEmotionDescription = uiState.emotionDescription,
                    emotionType = emotionType
                )
            }
        }
    }
}

@Composable
private fun CreatedText(createdAt: String) {
    val date = remember(createdAt) {
        LocalDate.parse(createdAt).format(DateTimeFormatter.ofPattern("yyyy. MM. dd."))
    }

    Text(
        text = "{$date}.",
        style = ByeBooTheme.typography.body5,
        color = ByeBooTheme.colors.gray400
    )
}

@Composable
private fun QuestEmotionDescriptionContent(
    questEmotionDescription: String,
    emotionType: LargeTagType,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_change),
                contentDescription = "title icon",
                tint = Color.Unspecified
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "퀘스트 완료 후, 이런 감정을 느꼈어요",
                color = ByeBooTheme.colors.gray200,
                style = ByeBooTheme.typography.body2
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        QuestEmotionDescriptionCard(
            questEmotionDescription = questEmotionDescription,
            emotionType = emotionType,
        )
    }
}
