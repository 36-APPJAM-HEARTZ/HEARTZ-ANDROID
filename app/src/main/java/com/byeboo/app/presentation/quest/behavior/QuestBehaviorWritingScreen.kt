package com.byeboo.app.presentation.quest.behavior

import android.net.Uri
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.byeboo.app.core.designsystem.component.button.ByeBooActivationButton
import com.byeboo.app.core.designsystem.component.tag.MiddleTag
import com.byeboo.app.core.designsystem.component.tag.SmallTag
import com.byeboo.app.core.designsystem.component.topbar.ByeBooTopBar
import com.byeboo.app.core.designsystem.type.MiddleTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.presentation.quest.behavior.component.QuestPhotoPicker
import androidx.compose.runtime.getValue
import com.byeboo.app.presentation.quest.component.QuestTextField
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext


@Composable
fun QuestBehaviorWritingScreen(
    //questContent: String ="",
    //onQuestContentChange: (String?) -> Unit,
    navigateToQuestComplete: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QuestBehaviorViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    //val writingState by viewModel.writingState.collectAsState()


    val coroutineScope = rememberCoroutineScope()


    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = ByeBooTheme.colors.black)
            .padding(horizontal = 24.dp, vertical = 10.dp)
    ) {

        item {
            ByeBooTopBar(
                onNavigateBack = {}
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SmallTag(
                    //tagText = "STEP ${uiState.stepNumber}",
                    tagText = "STEP 1"
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    //text = "${uiState.stepMissionTitle}",
                    text = "감정 정리하기",
                    color = ByeBooTheme.colors.gray500,
                    style = ByeBooTheme.typography.body2
                )

            }

            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Text(
                text = "1번째 퀘스트",
                color = ByeBooTheme.colors.secondary300,
                textAlign = TextAlign.Center,
                style = ByeBooTheme.typography.body5,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Text(
                //text = uiState.questTitle,
                text = "오늘은 나가서 상쾌하게 달리고 오세요.",
                color = ByeBooTheme.colors.gray100,
                textAlign = TextAlign.Center,
                style = ByeBooTheme.typography.head1
            )

            Spacer(modifier = Modifier.height(25.dp))
        }

        item {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                MiddleTag(
                    middleTagType = MiddleTagType.QUEST_TIP,
                    text = "작성 TIP"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

        }

        item {

            Row(verticalAlignment = Alignment.CenterVertically) {
                MiddleTag(middleTagType = MiddleTagType.QUEST_ESSENTIAL, text = "필수")

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "사진 첨부",
                    color = ByeBooTheme.colors.gray50,
                    style = ByeBooTheme.typography.body2
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    //text = "(${uiState.imageCount}/1)",
                    text = "(0/1)",
                    color = ByeBooTheme.colors.gray400,
                    style = ByeBooTheme.typography.body5
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

        }

        item {

            QuestPhotoPicker(
                //selectedImage = SelectedImage(uri = Uri.EMPTY),
                imageUrl = "",
                onImageClick = {},
            )

            Spacer(modifier = Modifier.height(16.dp))

        }

        item {

            Row(verticalAlignment = Alignment.CenterVertically) {
                MiddleTag(middleTagType = MiddleTagType.QUEST_OPTIONAL, text = "선택")

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "생각 적기",
                    color = ByeBooTheme.colors.gray50,
                    style = ByeBooTheme.typography.body2
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

        }

        item {

            QuestTextField(
                value = uiState.contents,
                onValueChange = viewModel::updateContent
            )

            Spacer(modifier = Modifier.height(21.dp))

        }

        item {

            ByeBooActivationButton(
                buttonDisableColor = ByeBooTheme.colors.whiteAlpha10,
                buttonText = "완료",
                buttonDisableTextColor = ByeBooTheme.colors.gray300,
                onClick = navigateToQuestComplete,
                isEnabled = true
            )

            Spacer(modifier = Modifier.padding(bottom = 56.dp))

        }

    }
}