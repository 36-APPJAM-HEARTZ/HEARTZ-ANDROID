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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.core.designsystem.component.button.ByeBooActivationButton
import com.byeboo.app.core.designsystem.component.tag.MiddleTag
import com.byeboo.app.core.designsystem.component.tag.SmallTag
import com.byeboo.app.core.designsystem.component.topbar.ByeBooTopBar
import com.byeboo.app.core.designsystem.type.MiddleTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.addFocusCleaner
import com.byeboo.app.domain.model.QuestContentLengthValidator
import com.byeboo.app.presentation.quest.component.QuestQuitModal
import com.byeboo.app.presentation.quest.component.QuestTextField

@Composable
fun QuestRecordingScreen(
    navigateToQuest: () -> Unit,
    navigateToQuestTip: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QuestRecordingViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val showQuitModal by viewModel.showQuitModal.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    // TODO: 바텀시트 올라오는 SideEffect / onCompleteClick
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                QuestRecordingSideEffect.NavigateToQuest -> navigateToQuest()
                QuestRecordingSideEffect.NavigateToQuestTip -> navigateToQuestTip()
                else -> ""
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
        ByeBooTopBar(
            modifier = Modifier.background(color = ByeBooTheme.colors.gray900Alpha80),
            onNavigateBack = viewModel::onBackClicked
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
                text = uiState.questTitle,
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
                    .clickable(onClick = viewModel::onTipClick)
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

            Spacer(modifier = Modifier.height(200.dp))

            ByeBooActivationButton(
                buttonDisableColor = ByeBooTheme.colors.whiteAlpha10,
                buttonText = "완료하기",
                buttonDisableTextColor = ByeBooTheme.colors.gray300,
                onClick = viewModel::onCompleteClick,
                isEnabled = QuestContentLengthValidator.validButton(uiState.contents)
            )

            Spacer(modifier = Modifier.padding(bottom = 56.dp))
        }
    }
}