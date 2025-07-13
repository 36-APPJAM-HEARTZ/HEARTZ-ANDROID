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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.component.button.ByeBooActivationButton
import com.byeboo.app.core.designsystem.component.tag.MiddleTag
import com.byeboo.app.core.designsystem.component.tag.SmallTag
import com.byeboo.app.core.designsystem.type.MiddleTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.presentation.quest.QuestViewModel
import com.byeboo.app.presentation.quest.behavior.component.QuestPhotoPicker
import com.byeboo.app.presentation.quest.component.QuestQuitModal
import com.byeboo.app.presentation.quest.component.QuestTextField
import com.byeboo.app.presentation.quest.component.bottomsheet.ByeBooBottomSheet
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestBehaviorWritingScreen(
    navigateToQuest: () -> Unit,
    navigateToQuestTip: (Int) -> Unit,
    navigateToQuestBehaviorComplete: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QuestBehaviorViewModel = hiltViewModel(),
    sharedViewModel: QuestViewModel,
) {
    val uiState by viewModel.state.collectAsState()
    val showBottomSheet by viewModel.showBottomSheet.collectAsState()
    val isEmotionSelected by viewModel.isEmotionSelected.collectAsState()
    val selectedImageUrl by viewModel.selectedImageUri.collectAsState()
    val showQuitModal by viewModel.showQuitModal.collectAsStateWithLifecycle()
    val selectedQuest by sharedViewModel.selectedQuest.collectAsStateWithLifecycle()


    if (showQuitModal) {
        QuestQuitModal(
            onDismissRequest = { viewModel.onDismissModal() },
            stayButton = {
                viewModel.onDismissModal()
            },
            quitButton = {
                viewModel.onDismissModal()
                viewModel.onQuitClick()
            }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest {
            when (it) {
                is QuestBehaviorSideEffect.NavigateToQuest -> navigateToQuest()
                is QuestBehaviorSideEffect.NavigateToQuestTip -> navigateToQuestTip(it.questId)
                is QuestBehaviorSideEffect.NavigateToQuestBehaviorComplete -> navigateToQuestBehaviorComplete(it.questID)
            }
        }
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
                    .clickable{viewModel.onBackClicked()}
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
                    text = "작성 TIP",
                    modifier = Modifier.clickable{viewModel.onTipClick()}
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
                    viewModel.updateSelectedImage(url)
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
                onValueChange = viewModel::updateContent
            )

            Spacer(modifier = Modifier.height(21.dp))
        }

        item {
            ByeBooActivationButton(
                buttonDisableColor = ByeBooTheme.colors.whiteAlpha10,
                buttonText = "완료",
                buttonDisableTextColor = ByeBooTheme.colors.gray300,
                onClick = {
                    viewModel.openBottomSheet()
                    viewModel.updateSelectedImage(selectedImageUrl)
                },
                // Todo: ContentLengthValidator 에서 호출
                // 사진이 있어야만 버튼 활성화
                isEnabled = true
            )

            Spacer(modifier = Modifier.padding(bottom = 56.dp))
        }
    }

    ByeBooBottomSheet(
        navigateButton = viewModel::onCompleteClick,
        showBottomSheet = showBottomSheet,
        onDismiss = {
            viewModel.closeBottomSheet()
        },
        onEmotionSelected = { selectedEmotion ->
            viewModel.isEmotionSelected(true)
            viewModel.updateSelectedEmotion(selectedEmotion)
            viewModel.closeBottomSheet()
        },
        onSelectedChanged = { isSelected ->
            viewModel.isEmotionSelected(isSelected)
        },
        isSelected = isEmotionSelected
    )
}
