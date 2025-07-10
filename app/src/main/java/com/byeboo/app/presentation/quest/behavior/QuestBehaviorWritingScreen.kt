package com.byeboo.app.presentation.quest.behavior

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.component.button.ByeBooActivationButton
import com.byeboo.app.core.designsystem.component.tag.MiddleTag
import com.byeboo.app.core.designsystem.component.tag.SmallTag
import com.byeboo.app.core.designsystem.type.MiddleTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.presentation.quest.behavior.component.QuestPhotoPicker
import com.byeboo.app.presentation.quest.component.QuestTextField
import com.byeboo.app.presentation.quest.component.QuitModal
import com.byeboo.app.presentation.quest.component.bottomsheet.ByeBooBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestBehaviorWritingScreen(
    navigateToQuestComplete: () -> Unit,
    modifier: Modifier = Modifier,
    sharedViewModel: QuestBehaviorViewModel = hiltViewModel()
) {
    val uiState by sharedViewModel.uiState.collectAsState()

    val showBottomSheet by sharedViewModel.showBottomSheet.collectAsState()

    val isEmotionSelected by sharedViewModel.isEmotionSelected.collectAsState()

    val selectedImageUrl by sharedViewModel.selectedImageUri.collectAsState()

    var showQuitModal by remember { mutableStateOf(false) }

    if (showQuitModal) {
        QuitModal(
            stayButton = { showQuitModal = false },
            quitButton = {
                showQuitModal = false
                // Todo: navigateToQuest() 이동
            }
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = ByeBooTheme.colors.black)
            .padding(horizontal = 24.dp, vertical = 10.dp)
    ) {
        item {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_left),
                contentDescription = "back button",
                tint = ByeBooTheme.colors.white,
                modifier = Modifier
                    .padding(top = 67.dp)
                    .clickable {
                        showQuitModal = true
                    }
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SmallTag(
                    tagText = "STEP ${uiState.stepNumber}"
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "${uiState.stepMissionTitle}",
                    color = ByeBooTheme.colors.gray500,
                    style = ByeBooTheme.typography.body2
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Text(
                text = "${uiState.questNumber}번째 퀘스트",
                color = ByeBooTheme.colors.secondary300,
                textAlign = TextAlign.Center,
                style = ByeBooTheme.typography.body5,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            Text(
                text = uiState.questTitle,
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
                    text = "(${uiState.imageCount}/1)",
                    color = ByeBooTheme.colors.gray400,
                    style = ByeBooTheme.typography.body5
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            QuestPhotoPicker(
                imageUrl = selectedImageUrl,
                onImageClick = { url ->
                    sharedViewModel.updateSelectedImage(url)
                }
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
                questWritingState = uiState.contentState,
                value = uiState.contents,
                onValueChange = sharedViewModel::updateContent,
                placeholder = "꼭 적지 않아도 괜찮지만, 글로 정리해보면 스스로에게 한 걸음 더 가까워질 수 있어요."
            )

            Spacer(modifier = Modifier.height(21.dp))
        }

        item {
            ByeBooActivationButton(
                buttonDisableColor = ByeBooTheme.colors.whiteAlpha10,
                buttonText = "완료",
                buttonDisableTextColor = ByeBooTheme.colors.gray300,
                onClick = {
                    sharedViewModel.openBottomSheet()
                    sharedViewModel.updateSelectedImage(selectedImageUrl)
                },
                // Todo: ContentLengthValidator 에서 호출
                isEnabled = true
            )

            Spacer(modifier = Modifier.padding(bottom = 56.dp))
        }
    }

    ByeBooBottomSheet(
        navigateButton = navigateToQuestComplete,
        showBottomSheet = showBottomSheet,
        onDismiss = {
            sharedViewModel.closeBottomSheet()
        },
        onEmotionSelected = { selectedEmotion ->
            sharedViewModel.isEmotionSelected(true)
            sharedViewModel.updateSelectedEmotion(selectedEmotion)
            sharedViewModel.closeBottomSheet()
        },
        onSelectedChanged = { isSelected ->
            sharedViewModel.isEmotionSelected(isSelected)
        },
        isSelected = isEmotionSelected
    )
}
