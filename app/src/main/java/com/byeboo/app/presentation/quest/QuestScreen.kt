package com.byeboo.app.presentation.quest

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
import com.byeboo.app.core.model.QuestType
import com.byeboo.app.presentation.quest.component.QuestBox
import com.byeboo.app.presentation.quest.component.QuestModal
import com.byeboo.app.presentation.quest.component.QuestStepTitle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun QuestScreen(
    navigateToQuestTip: (Int) -> Unit,
    navigateToQuestRecording: (Int) -> Unit,
    navigateToQuestBehavior: (Int) -> Unit,
    navigateToQuestReview: (Int, QuestType) -> Unit,
    navigateToHome: () -> Unit,
    bottomPadding: Dp,
    viewModel: QuestViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val questGroups by viewModel.questGroups.collectAsStateWithLifecycle()
    val currentStepIndex by viewModel.currentStepIndex.collectAsStateWithLifecycle()
    val showQuitModal by viewModel.showQuitModal.collectAsStateWithLifecycle()
    val gridState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest {
            when (it) {
                is QuestSideEffect.NavigateToQuestTip -> navigateToQuestTip(it.questId)
                is QuestSideEffect.NavigateToQuestRecording -> navigateToQuestRecording(it.questId)
                is QuestSideEffect.NavigateToQuestBehavior -> navigateToQuestBehavior(it.questId)
                is QuestSideEffect.NavigateToQuestReview -> navigateToQuestReview(
                    it.questId,
                    it.questType
                )

                is QuestSideEffect.NavigateToHome -> navigateToHome()
            }
        }
    }
    LaunchedEffect(currentStepIndex, questGroups) {
        val scrollIndex = questGroups
            .take(currentStepIndex)
            .sumOf { it.quests.size + 1 }

        gridState.animateScrollToItem(index = scrollIndex)
    }

    BackHandler { viewModel.onBackClick() }

    if (showQuitModal) {
        QuestModal(
            onDismissRequest = { viewModel.onDismissModal() },
            questId = uiState.questId,
            questQuestion = uiState.questQuestion,
            navigateToTip = viewModel::onTipClick,
            progressButton = viewModel::onQuestStart
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ByeBooTheme.colors.black)
            .padding(horizontal = 24.dp)
            .padding(top = 67.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            MiddleTag(
                middleTagType = MiddleTagType.QUEST_START_DAY,
                text = "3"
            )
            DescriptionText(
                nicknameText = "하츠핑님, 지금",
                title = "감정 직면 여정",
                guideText = "을 진행 중이에요.",
                contentText = "오늘도 한 걸음 나아가볼까요?"
            )
        }
        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .background(ByeBooTheme.colors.black),
            horizontalArrangement = Arrangement.spacedBy(21.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(bottom = bottomPadding + 37.dp)
        ) {
            questGroups.forEachIndexed { stepIndex, group ->
                item(
                    span = { GridItemSpan(3) },
                    key = group.stepTitle
                ) {
                    Column {
                        HorizontalDivider(
                            thickness = 1.dp,
                            color = ByeBooTheme.colors.whiteAlpha10,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        QuestStepTitle(
                            stepNumber = stepIndex + 1,
                            stepTitle = group.stepTitle
                        )
                        Spacer(modifier = Modifier.padding(top = 8.dp))
                    }
                }
                group.quests.forEach { quest ->
                    item(
                        key = quest.questNumber
                    ) {
                        QuestBox(
                            questId = quest.questId,
                            questNumber = quest.questNumber,
                            state = quest.state,
                            onQuestClick = viewModel::onQuestClick
                        )
                    }
                }
            }
        }
    }
}
