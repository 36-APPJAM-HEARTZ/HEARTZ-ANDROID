package com.byeboo.app.presentation.quest.record

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.component.tag.SmallTag
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
    questId: Long,
    navigateToQuest: () -> Unit,
    bottomPadding: Dp,
    modifier: Modifier = Modifier,
    viewModel: QuestRecordingCompleteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(questId) {
        viewModel.setQuestId(questId)
        viewModel.getQuestRecordedDetail(questId)
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is QuestRecordingCompleteSideEffect.NavigateToQuest -> navigateToQuest()
            }
        }
    }

    BackHandler { viewModel.onCloseClick() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = ByeBooTheme.colors.black)
            .padding(horizontal = 24.dp)
            .padding(bottom = bottomPadding),
    ) {
        Spacer(modifier = Modifier.height(67.dp))

        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_left),
            contentDescription = "back button",
            tint = ByeBooTheme.colors.white,
            modifier = Modifier
                .clickable { viewModel.onCloseClick() }
                .align(alignment = Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(8.dp))

                QuestCompleteCard(
                    modifier = Modifier.fillMaxWidth()
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    SmallTag(tagText = "STEP ${uiState.stepNumber}")

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "${uiState.questNumber}번째 퀘스트",
                        style = ByeBooTheme.typography.body2,
                        color = ByeBooTheme.colors.gray500
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    CreatedText(uiState.createdAt)

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = uiState.question,
                        style = ByeBooTheme.typography.head1,
                        color = ByeBooTheme.colors.gray100,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    QuestContent(
                        titleIcon = QuestContentType.THINKING,
                        titleText = "이렇게 생각했어요",
                        contentText = uiState.answer
                    )

                    Spacer(modifier = Modifier.height(48.dp))

                    QuestEmotionDescriptionContent(
                        questEmotionDescription = uiState.emotionDescription,
                        emotionType = uiState.selectedEmotion
                    )
                }
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
        text = "$date.",
        style = ByeBooTheme.typography.body5,
        color = ByeBooTheme.colors.gray400
    )
}

@Composable
private fun QuestEmotionDescriptionContent(
    questEmotionDescription: String,
    emotionType: LargeTagType,
    modifier: Modifier = Modifier
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
            emotionType = emotionType
        )
    }
}
