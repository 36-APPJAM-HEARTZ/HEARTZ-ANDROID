package com.byeboo.app.presentation.quest.behavior

import QuestPhotoPicker
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.component.button.ByeBooActivationButton
import com.byeboo.app.core.designsystem.component.tag.MiddleTag
import com.byeboo.app.core.designsystem.component.tag.SmallTag
import com.byeboo.app.core.designsystem.type.MiddleTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.model.quest.QuestType
import com.byeboo.app.core.util.addFocusCleaner
import com.byeboo.app.core.util.screenHeightDp
import com.byeboo.app.core.util.screenWidthDp
import com.byeboo.app.domain.model.quest.QuestValidator
import com.byeboo.app.presentation.quest.component.bottomsheet.ByeBooBottomSheet
import com.byeboo.app.presentation.quest.component.modal.QuestQuitModal
import com.byeboo.app.presentation.quest.component.text.textfield.QuestTextField
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestBehaviorWritingScreen(
    questId: Long,
    navigateToQuest: () -> Unit,
    navigateToQuestTip: (Long, QuestType) -> Unit,
    navigateToQuestBehaviorComplete: (Long) -> Unit,
    bottomPadding: Dp,
    modifier: Modifier = Modifier,
    viewModel: QuestBehaviorViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val isFocused = remember { mutableStateOf(false) }

    LaunchedEffect(questId) {
        viewModel.setQuestId(questId)
        viewModel.getQuestDetailInfo(questId)
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest {
            when (it) {
                is QuestBehaviorSideEffect.NavigateToQuest -> navigateToQuest()
                is QuestBehaviorSideEffect.NavigateToQuestTip -> navigateToQuestTip(
                    it.questId,
                    it.questType
                )

                is QuestBehaviorSideEffect.NavigateToQuestBehaviorComplete -> navigateToQuestBehaviorComplete(
                    it.questId
                )

                is QuestBehaviorSideEffect.CompleteAndClear -> {
                    viewModel.clearQuestInput()
                    navigateToQuestBehaviorComplete(it.questId)
                }
            }
        }
    }

    if (uiState.showQuitModal) {
        QuestQuitModal(
            onDismissRequest = { viewModel.onDismissModal() },
            stayButton = {
                viewModel.onDismissModal()
            },
            quitButton = {
                viewModel.onDismissModal()
                viewModel.onQuitClick()
            },
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }

    LaunchedEffect(isFocused.value) {
        if (isFocused.value) {
            delay(300)
            bringIntoViewRequester.bringIntoView()
        }
    }

    BackHandler { viewModel.onBackClicked() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = ByeBooTheme.colors.black)
            .addFocusCleaner(focusManager)
            .padding(horizontal = screenWidthDp(24.dp))
            .padding(bottom = screenHeightDp(bottomPadding))
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_left),
            contentDescription = "back button",
            tint = ByeBooTheme.colors.white,
            modifier = Modifier
                .padding(
                    top = screenHeightDp((27.dp) + bottomPadding),
                    bottom = screenHeightDp(16.dp)
                )
                .align(Alignment.Start)
                .clickable { viewModel.onBackClicked() }
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SmallTag(
                        tagText = "STEP ${uiState.stepNumber}",
                        tagColor = ByeBooTheme.colors.gray300
                    )

                    Spacer(modifier = Modifier.width(screenWidthDp(12.dp)))

                    Text(
                        text = "${uiState.stepMissionTitle}",
                        color = ByeBooTheme.colors.gray500,
                        style = ByeBooTheme.typography.body2
                    )
                }

                Spacer(modifier = Modifier.height(screenHeightDp(12.dp)))
            }

            item {
                Text(
                    text = "${uiState.questNumber}번째 퀘스트",
                    color = ByeBooTheme.colors.secondary300,
                    textAlign = TextAlign.Center,
                    style = ByeBooTheme.typography.body5,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(screenHeightDp(12.dp)))
            }

            item {
                Text(
                    text = uiState.question,
                    color = ByeBooTheme.colors.gray100,
                    textAlign = TextAlign.Center,
                    style = ByeBooTheme.typography.head1,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(screenHeightDp(25.dp)))
            }

            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    MiddleTag(
                        middleTagType = MiddleTagType.QUEST_TIP,
                        text = "작성 TIP",
                        modifier = Modifier.clickable { viewModel.onTipClick() }
                    )
                }

                Spacer(modifier = Modifier.height(screenHeightDp(16.dp)))
            }

            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MiddleTag(middleTagType = MiddleTagType.QUEST_ESSENTIAL, text = "필수")

                    Spacer(modifier = Modifier.width(screenWidthDp(8.dp)))

                    Text(
                        text = "사진 첨부",
                        color = ByeBooTheme.colors.gray50,
                        style = ByeBooTheme.typography.body2
                    )

                    Spacer(modifier = Modifier.width(screenWidthDp(8.dp)))

                    Text(
                        text = "(${uiState.imageCount}/1)",
                        color = ByeBooTheme.colors.gray400,
                        style = ByeBooTheme.typography.body5
                    )
                }

                Spacer(modifier = Modifier.height(screenHeightDp(8.dp)))
            }

            item {
                QuestPhotoPicker(
                    imageUrl = uiState.selectedImageUri,
                    onImageClick = { url ->
                        viewModel.updateSelectedImage(url)
                    }
                )

                Spacer(modifier = Modifier.height(screenHeightDp(16.dp)))
            }

            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MiddleTag(middleTagType = MiddleTagType.QUEST_OPTIONAL, text = "선택")

                    Spacer(modifier = Modifier.width(screenWidthDp(8.dp)))

                    Text(
                        text = "생각 적기",
                        color = ByeBooTheme.colors.gray50,
                        style = ByeBooTheme.typography.body2
                    )
                }

                Spacer(modifier = Modifier.height(screenHeightDp(8.dp)))
            }

            item {
                Column {
                    QuestTextField(
                        questWritingState = uiState.contentState,
                        value = uiState.contents,
                        onValueChange = {
                            if (it.length <= 200) {
                                viewModel.updateContent(isFocused = true, it)
                            }
                        },
                        placeholder = "꼭 적지 않아도 괜찮지만, 글로 정리해보면 스스로에게 한 걸음 더 가까워질 수 있어요.",
                        isQuestion = false,
                        onFocusChanged = {
                            isFocused.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .bringIntoViewRequester(bringIntoViewRequester)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(screenHeightDp(24.dp)))

                ByeBooActivationButton(
                    buttonDisableColor = ByeBooTheme.colors.whiteAlpha10,
                    buttonText = "완료",
                    buttonDisableTextColor = ByeBooTheme.colors.gray300,
                    onClick = {
                        viewModel.openBottomSheet()
                        viewModel.updateSelectedImage(uiState.selectedImageUri)
                    },
                    isEnabled = QuestValidator.validButton(uiState.imageCount)
                )
            }
        }
    }

    ByeBooBottomSheet(
        navigateButton = { viewModel.uploadImage(context) },
        showBottomSheet = uiState.showBottomSheet,
        onDismiss = {
            viewModel.closeBottomSheet()
        },
        onEmotionSelected = { selectedEmotion ->
            viewModel.isEmotionSelected(true)
            viewModel.updateSelectedEmotion(selectedEmotion)
        },
        onSelectedChanged = { isSelected ->
            viewModel.isEmotionSelected(isSelected)
        },
        isSelected = uiState.isEmotionSelected,
        isUploading = uiState.isUploading
    )
}
