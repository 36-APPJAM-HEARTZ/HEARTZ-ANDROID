package com.byeboo.app.presentation.quest.behavior

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.byeboo.app.core.designsystem.component.contenttext.ContentText
import com.byeboo.app.core.designsystem.component.tag.SmallTag
import com.byeboo.app.core.designsystem.component.topbar.ByeBooTopBar
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.presentation.quest.component.QuestCompleteCard
import com.byeboo.app.presentation.quest.component.QuestCompleteTitle
import com.byeboo.app.presentation.quest.component.QuestEmotionDescriptionCard
import com.byeboo.app.presentation.quest.component.QuestTitle
import com.byeboo.app.presentation.quest.navigation.Quest

@Composable
fun QuestBehaviorCompleteScreen(
    viewModel: QuestBehaviorViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    val selectedImageUrl by viewModel.selectedImageUri.collectAsState()


    ByeBooTopBar(onCloseClick = {})

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            QuestCompleteCard(modifier = Modifier.padding(top = 67.dp))
            Spacer(modifier = Modifier.height(32.dp))
        }
        item {

                QuestCompleteTitle(
                    stepNumber = uiState.stepNumber,
                    questNumber = uiState.questNumber,
                    createdAt = uiState.createdAt,
                    questQuestion = uiState.questTitle,

                )

        }

        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .width(312.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .aspectRatio(1f)
                ) {

                    selectedImageUrl?.let { url ->
                        AsyncImage(
                            model = url,
                            contentDescription = "uploaded Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                ContentText(uiState.contents)


            }
        }

        item {
                QuestEmotionDescriptionCard(
                    modifier = Modifier,
                    emotionType = uiState.selectedEmotion
                )

        }

}


}


@Preview
@Composable
private fun QuestBehaviorCompletePreview() {
    ByeBooTheme {
        QuestBehaviorCompleteScreen()

    }
}
