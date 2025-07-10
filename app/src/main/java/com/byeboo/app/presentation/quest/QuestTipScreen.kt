package com.byeboo.app.presentation.quest

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: QuestTipViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                QuestTipSideEffect.NavigateBack -> navigateBack()
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = ByeBooTheme.colors.black)
    ) {
        ByeBooTopBar(
            title = "퀘스트 작성 TIP",
            onCloseClick = viewModel::onCloseClick
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
                SmallTag(tagText = "STEP ${uiState.stepNumber}")

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${uiState.questNumber}번째 퀘스트",
                    style = ByeBooTheme.typography.body2,
                    color = ByeBooTheme.colors.gray500
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = uiState.question,
                style = ByeBooTheme.typography.head1,
                color = ByeBooTheme.colors.gray100,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height((35.5).dp))

            QuestContent(
                titleIcon = QuestContentType.THINKING,
                titleText = uiState.tipQuestion[0],
                contentText = uiState.tipAnswer[0]
            )

            Spacer(modifier = Modifier.height(32.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = ByeBooTheme.colors.whiteAlpha10
            )

            Spacer(modifier = Modifier.height(32.dp))

            QuestContent(
                titleIcon = QuestContentType.QUEST_REASON,
                titleText = uiState.tipQuestion[1],
                contentText = uiState.tipAnswer[1]
            )

            Spacer(modifier = Modifier.height(32.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = ByeBooTheme.colors.whiteAlpha10
            )

            Spacer(modifier = Modifier.height(32.dp))

            QuestContent(
                titleIcon = QuestContentType.FEELING_CHANGE,
                titleText = uiState.tipQuestion[2],
                contentText = uiState.tipAnswer[2]
            )
        }
    }
}