package com.byeboo.app.presentation.quest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.byeboo.app.core.designsystem.component.tag.MiddleTag
import com.byeboo.app.core.designsystem.component.text.DescriptionText
import com.byeboo.app.core.designsystem.type.MiddleTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.model.quest.QuestType
import com.byeboo.app.core.util.screenHeightDp
import com.byeboo.app.core.util.screenWidthDp
import com.byeboo.app.presentation.quest.model.QuestSideEffect
import com.byeboo.app.presentation.quest.component.chip.QuestBox
import com.byeboo.app.presentation.quest.component.modal.QuestModal
import com.byeboo.app.presentation.quest.component.text.QuestStepTitle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun QuestScreen(
    navigateToQuestTip: (Long, QuestType) -> Unit,
    navigateToQuestRecording: (Long) -> Unit,
    navigateToQuestBehavior: (Long) -> Unit,
    navigateToQuestReview: (Long) -> Unit,
    navigateToHome: () -> Unit,
    bottomPadding: Dp,
    viewModel: QuestViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    LaunchedEffect(uiState.currentStepIndex) {
        if (uiState.questGroups.isNotEmpty() && uiState.currentStepIndex >= 0) {
            val scrollIndex = uiState.questGroups
                .take(uiState.currentStepIndex)
                .sumOf { group ->
                    1 + (group.quests.size + 2) / 3
                }
            listState.animateScrollToItem(index = scrollIndex)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest {
            when (it) {
                is QuestSideEffect.NavigateToQuestTip -> navigateToQuestTip(
                    it.questId,
                    it.questType
                )

                is QuestSideEffect.NavigateToQuestRecording -> navigateToQuestRecording(it.questId)
                is QuestSideEffect.NavigateToQuestBehavior -> navigateToQuestBehavior(it.questId)
                is QuestSideEffect.NavigateToQuestReview -> navigateToQuestReview(it.questId)
                is QuestSideEffect.NavigateToHome -> navigateToHome()
            }
        }
    }

    if (uiState.showQuitModal) {
        QuestModal(
            onDismissRequest = { viewModel.onDismissModal() },
            questNumber = uiState.selectedQuest?.questNumber ?: 0L,
            questQuestion = uiState.selectedQuest?.questQuestion ?: "",
            navigateToTip = viewModel::onTipClick,
            progressButton = viewModel::onQuestStart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = screenWidthDp(24.dp))
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ByeBooTheme.colors.black)
            .padding(horizontal = screenWidthDp(24.dp))
            .padding(top = screenHeightDp(67.dp))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            MiddleTag(
                middleTagType = MiddleTagType.QUEST_START_DAY,
                text = uiState.progressPeriod.toString()
            )

            Spacer(modifier = Modifier.height(8.dp))

            DescriptionText(
                nicknameText = "${uiState.userName}님, 지금",
                title = "${uiState.journeyTitle} 여정",
                guideText = "을 진행 중이에요",
                contentText = "오늘도 한 걸음 나아가볼까요?",
                bottom = 18.dp
            )
        }
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .background(ByeBooTheme.colors.black),
            verticalArrangement = Arrangement.spacedBy(screenWidthDp(20.dp)),
            contentPadding = PaddingValues(bottom = screenHeightDp(bottomPadding + 37.dp))
        ) {
            uiState.questGroups.forEachIndexed { stepIndex, group ->
                item(key = "header_$stepIndex") {
                    Column {
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = ByeBooTheme.colors.whiteAlpha10,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        QuestStepTitle(
                            stepNumber = (stepIndex + 1).toLong(),
                            stepTitle = group.stepTitle
                        )
                        Spacer(modifier = Modifier.padding(top = 8.dp))
                    }
                }
                val questChunks = group.quests.chunked(3)
                questChunks.forEachIndexed { chunkIndex, questChunk ->
                    item(key = "quest_row_${stepIndex}_$chunkIndex") {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(screenWidthDp(21.dp))
                        ) {
                            questChunk.forEach { quest ->
                                QuestBox(
                                    modifier = Modifier.weight(1f),
                                    questId = quest.questId,
                                    questNumber = quest.questNumber,
                                    state = quest.state,
                                    onQuestClick = viewModel::onQuestClick
                                )
                            }
                            repeat(3 - questChunk.size) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }
    }
}
