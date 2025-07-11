package com.byeboo.app.presentation.quest.record

import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.component.button.ByeBooActivationButton
import com.byeboo.app.core.designsystem.component.tag.MiddleTag
import com.byeboo.app.core.designsystem.component.tag.SmallTag
import com.byeboo.app.core.designsystem.type.MiddleTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.QuestFromParent
import com.byeboo.app.core.util.addFocusCleaner
import com.byeboo.app.domain.model.QuestContentLengthValidator
import com.byeboo.app.presentation.quest.QuestViewModel
import com.byeboo.app.presentation.quest.component.QuestQuitModal
import com.byeboo.app.presentation.quest.component.QuestTextField
import com.byeboo.app.presentation.quest.component.bottomsheet.ByeBooBottomSheet
import com.byeboo.app.presentation.quest.navigation.rememberQuestViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestRecordingScreen(
    navController: NavController,
    navigateToQuest: () -> Unit,
    navigateToQuestTip: (Int) -> Unit,
    navigateToQuestRecordingComplete: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QuestRecordingViewModel = hiltViewModel(),
    sharedViewModel: QuestViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val showQuitModal by viewModel.showQuitModal.collectAsStateWithLifecycle()
    val showBottomSheet by viewModel.showBottomSheet.collectAsState()
    val isEmotionSelected by viewModel.isEmotionSelected.collectAsState()
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    val selectedQuest by sharedViewModel.selectedQuest.collectAsStateWithLifecycle()

    Log.d("QuestDebug", "selectedQuest = $selectedQuest")

    LaunchedEffect(selectedQuest?.questId) {
        selectedQuest?.let { quest ->
            viewModel.setQuestId(quest.questId)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest {
            when (it) {
                is QuestRecordingSideEffect.NavigateToQuest -> navigateToQuest()
                is QuestRecordingSideEffect.NavigateToQuestTip -> navigateToQuestTip(it.questId)
                is QuestRecordingSideEffect.NavigateToQuestRecordingComplete -> navigateToQuestRecordingComplete(it.questId)
            }
        }
    }

    if (showQuitModal) {
        QuestQuitModal(
            onDismissRequest = {viewModel.onDismissModal()},
            stayButton = {
                viewModel.onDismissModal()
            },
            quitButton = {
                viewModel.onDismissModal()
                viewModel.onQuitClick()
            }
        )
    }

    BackHandler { viewModel.onBackClicked() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = ByeBooTheme.colors.black)
            .addFocusCleaner(focusManager)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_left),
            contentDescription = "back button",
            tint = ByeBooTheme.colors.white,
            modifier = Modifier
                .padding(top = 67.dp)
                .clickable{viewModel.onBackClicked()}
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SmallTag(
                    tagText = "STEP ${uiState.stepNumber}",
                    tagColor = ByeBooTheme.colors.gray300,
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = uiState.step,
                    style = ByeBooTheme.typography.body2,
                    color = ByeBooTheme.colors.gray500
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "${uiState.questNumber}번째 퀘스트",
                color = ByeBooTheme.colors.secondary300,
                style = ByeBooTheme.typography.body5,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = uiState.questQuestion,
                color = ByeBooTheme.colors.gray100,
                style = ByeBooTheme.typography.head1,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(25.dp))

            MiddleTag(
                middleTagType = MiddleTagType.QUEST_TIP,
                text = "작성 TIP",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable{viewModel.onTipClick()}
            )

            Spacer(modifier = Modifier.height(24.dp))

            QuestTextField(
                questWritingState = uiState.contentsState,
                value = uiState.contents,
                onValueChange = {
                    if (it.length <= 500) {
                        viewModel.updateContent(it)
                    }
                },
                placeholder = "글로 적다 보면, 스스로에게 한 걸음 더 가까워질 수 있어요."
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "*10글자 이상 입력해주세요.",
                style = ByeBooTheme.typography.cap2,
                color = ByeBooTheme.colors.gray400,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            //TODO: height를 고정 값으로 주지 않고 스크롤 안 시킬 수 있는 방법은...?
            Spacer(modifier = Modifier.height(200.dp))

            ByeBooActivationButton(
                buttonDisableColor = ByeBooTheme.colors.whiteAlpha10,
                buttonText = "완료하기",
                buttonDisableTextColor = ByeBooTheme.colors.gray300,
                onClick = viewModel::openBottomSheet,
                isEnabled = QuestContentLengthValidator.validButton(uiState.contents)
            )

            Spacer(modifier = Modifier.height(56.dp))        }
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