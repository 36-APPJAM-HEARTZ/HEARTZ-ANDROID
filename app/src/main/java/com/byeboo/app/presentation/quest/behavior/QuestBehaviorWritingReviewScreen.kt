package com.byeboo.app.presentation.quest.behavior

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.component.contenttext.ContentText
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.presentation.quest.component.QuestEmotionDescriptionCard
import com.byeboo.app.presentation.quest.component.QuestTitle

@Composable
fun QuestBehaviorWritingReviewScreen(
    sharedViewModel: QuestBehaviorViewModel = hiltViewModel()
) {
    val uiState by sharedViewModel.uiState.collectAsState()

    val selectedImageUri by sharedViewModel.selectedImageUri.collectAsState()

    LazyColumn(
        modifier = Modifier
            .background(ByeBooTheme.colors.black)
            .padding(horizontal = 24.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 67.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_cancel),
                    contentDescription = "back button",
                    tint = ByeBooTheme.colors.white,
                    modifier = Modifier.clickable {
                        // TODO: QuestToHome navigate
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            QuestTitle(
                stepNumber = uiState.stepNumber,
                questNumber = uiState.questNumber,
                createdAt = uiState.createdAt,
                questQuestion = uiState.questTitle
            )

            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            Row {
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
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                selectedImageUri?.let { uri ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current).data(uri)
                            .crossfade(true).build(),
                        contentDescription = "uploaded image",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (uiState.contents.isNotBlank()) {
                ContentText(uiState.contents)
            }
        }

        item {
            Column(
                modifier = Modifier.padding(vertical = 24.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_change),
                        contentDescription = "title icon",
                        modifier = Modifier.padding(end = 8.dp),
                        tint = Color.Unspecified
                    )

                    Text(
                        text = "퀘스트 완료 후, 이런 감정을 느꼈어요",
                        color = ByeBooTheme.colors.gray200,
                        style = ByeBooTheme.typography.body2
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                QuestEmotionDescriptionCard(
                    modifier = Modifier,
                    emotionType = uiState.selectedEmotion
                )
            }
        }
    }
}
