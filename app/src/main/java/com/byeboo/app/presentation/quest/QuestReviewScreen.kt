package com.byeboo.app.presentation.quest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.component.text.ContentText
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.model.QuestType
import com.byeboo.app.presentation.quest.component.QuestContent
import com.byeboo.app.presentation.quest.component.QuestEmotionDescriptionCard
import com.byeboo.app.presentation.quest.component.QuestTitle
import com.byeboo.app.presentation.quest.component.type.QuestContentType
import kotlinx.coroutines.flow.collectLatest

@Composable
fun QuestReviewScreen(
    questId: Long,
    navigateToQuest: () -> Unit,
    viewModel: QuestReviewViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val selectedImageUri by viewModel.selectedImageUri.collectAsState()

    LaunchedEffect(questId) {
        viewModel.setQuestId(questId)
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { effect ->
            when (effect) {
                QuestReviewSideEffect.NavigateToQuest -> navigateToQuest()
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = ByeBooTheme.colors.black)
            .padding(horizontal = 24.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 67.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_cancel),
                    contentDescription = "back button",
                    tint = ByeBooTheme.colors.white,
                    modifier = Modifier.clickable {
                        viewModel.onCloseClick()
                    }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
        }

        item {
            QuestTitle(
                stepNumber = uiState.stepNumber,
                questNumber = uiState.stepNumber,
                createdAt = uiState.createdAt,
                questQuestion = uiState.questQuestion
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            when (uiState.type) {
                QuestType.EMOTION_FACE ->
                    QuestContent(
                        titleIcon = QuestContentType.THINKING,
                        titleText = "이렇게 생각했어요",
                        contentText = uiState.questAnswer
                    )

                QuestType.EMOTION_ORGANIZE -> {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_think),
                            contentDescription = "title icon",
                            modifier = Modifier.padding(end = 8.dp),

                            tint = Color.Unspecified
                        )

                        Text(
                            text = "이렇게 완료했어요",
                            color = ByeBooTheme.colors.gray200,
                            style = ByeBooTheme.typography.body2
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(360 / 312f)
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        selectedImageUri?.let { uri ->
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f),
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(uri)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = "uploaded image",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    if (uiState.questAnswer.isNotBlank()) {
                        ContentText(uiState.questAnswer)
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(48.dp))

            QuestEmotionDescriptionContent(
                questEmotionDescription = uiState.emotionDescription,
                emotionType = uiState.emotion
            )

            Spacer(modifier = Modifier.height(28.dp))
        }
    }
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

        Spacer(modifier = Modifier.height(24.dp))
    }
}
