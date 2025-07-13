package com.byeboo.app.presentation.quest

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.core.designsystem.component.tag.SmallTag
import com.byeboo.app.core.designsystem.component.topbar.ByeBooTopBar
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.presentation.quest.component.QuestContent
import com.byeboo.app.presentation.quest.component.type.QuestContentType

@Composable
fun QuestTipScreen(
    questId: Long,
    navigateBack: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QuestTipViewModel = hiltViewModel(),
    questViewModel: QuestViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val selectedQuest by questViewModel.selectedQuest.collectAsStateWithLifecycle()

    LaunchedEffect(questId) {
        val questGroups = questViewModel.questGroups.value

        val quest = questGroups
            .flatMap { it.quests }
            .find { it.questId == questId }

        if (quest != null) {
            viewModel.loadQuestTip(quest)
        }
    }

    LaunchedEffect(selectedQuest?.questId) {
        selectedQuest?.let { quest ->
            if (quest.questId == questId) {
                viewModel.loadQuestTip(quest)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is QuestTipSideEffect.NavigateBack -> {
                    navigateBack(questId)
                }
            }
        }
    }

    BackHandler { viewModel.onCloseClick() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = ByeBooTheme.colors.black)
    ) {
        ByeBooTopBar(
            title = "퀘스트 작성 TIP",
            onCloseClick = {
                viewModel.onCloseClick()
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SmallTag(tagText = "STEP ${uiState.stepNumber}")

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "${uiState.questNumber}번째 퀘스트",
                        style = ByeBooTheme.typography.body2,
                        color = ByeBooTheme.colors.gray500
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = uiState.question,
                    style = ByeBooTheme.typography.head1,
                    color = ByeBooTheme.colors.gray100,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            item {
                Spacer(modifier = Modifier.height((35.5).dp))

                QuestContent(
                    titleIcon = QuestContentType.THINKING,
                    titleText = if (uiState.tipQuestion.isNotEmpty()) uiState.tipQuestion[0] else "",
                    contentText = if (uiState.tipAnswer.isNotEmpty()) uiState.tipAnswer[0] else ""
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = ByeBooTheme.colors.whiteAlpha10
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))

                QuestContent(
                    titleIcon = QuestContentType.QUEST_REASON,
                    titleText = if (uiState.tipQuestion.size > 1) uiState.tipQuestion[1] else "",
                    contentText = if (uiState.tipAnswer.size > 1) uiState.tipAnswer[1] else ""
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = ByeBooTheme.colors.whiteAlpha10
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))

                QuestContent(
                    titleIcon = QuestContentType.FEELING_CHANGE,
                    titleText = if (uiState.tipQuestion.size > 2) uiState.tipQuestion[2] else "",
                    contentText = if (uiState.tipAnswer.size > 2) uiState.tipAnswer[2] else ""
                )
            }
        }
    }
}
