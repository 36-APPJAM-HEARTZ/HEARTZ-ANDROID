package com.byeboo.app.presentation.quest.component.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.component.button.ByeBooActivationButton
import com.byeboo.app.core.designsystem.component.chip.EmotionChip
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ByeBooBottomSheet(
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    isBackgroundDimmed: Boolean = true,
    dragHandle: @Composable () -> Unit = {},
    isSelected: Boolean = false
) {

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        modifier = modifier,
        sheetState = sheetState,
        containerColor = ByeBooTheme.colors.gray900Alpha80,
        scrimColor = if (isBackgroundDimmed) ByeBooTheme.colors.blackAlpha80 else Color.Transparent,
        dragHandle = dragHandle
    ) {

        val density = LocalDensity.current
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val currentHeight = with(density) {
            (screenHeight * 0.66375f).toPx()
        }

        var selectedEmotion by remember { mutableStateOf<LargeTagType?>(null) }

        var buttonText = if (isSelected) "선택 완료" else "완료"
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(with(density) { currentHeight.toDp() })
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "퀘스트를 완료한 후,\n어떤 감정이 느껴지시나요?",
                color = ByeBooTheme.colors.gray50,
                textAlign = TextAlign.Center,
                style = ByeBooTheme.typography.head1

            )

            Spacer(modifier = Modifier.height(20.dp))

            EmotionChipList(
                selectedEmotion = selectedEmotion,
                onEmotionSelected = {

                        selectedEmotion = if (selectedEmotion == it) null else it
                }
            )

            Spacer(modifier = Modifier.height(37.dp))

            ByeBooActivationButton(
                buttonDisableColor = ByeBooTheme.colors.whiteAlpha10,
                buttonText =  buttonText,
                buttonDisableTextColor = ByeBooTheme.colors.gray300,
                onClick = {},
                isEnabled = isSelected
            )
        }
    }
}

@Composable
fun EmotionChipList(
    selectedEmotion: LargeTagType?,
    onEmotionSelected: (LargeTagType) -> Unit,
    modifier: Modifier = Modifier,
)  {
    Column(modifier = modifier.padding(horizontal = 62.dp)) {

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center)
        {
            EmotionChip(
                emotionType = LargeTagType.EMOTION_NEUTRAL,
                isSelected = selectedEmotion == LargeTagType.EMOTION_NEUTRAL,
                onChipClick = {onEmotionSelected(LargeTagType.EMOTION_NEUTRAL)}
            )

            Spacer(modifier = Modifier.width(20.dp))
            EmotionChip(
                emotionType = LargeTagType.EMOTION_SELF_AWARE,
                isSelected = selectedEmotion == LargeTagType.EMOTION_SELF_AWARE,
                onChipClick = { onEmotionSelected(LargeTagType.EMOTION_SELF_AWARE) }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center)
        {
            EmotionChip(
                emotionType = LargeTagType.EMOTION_SADNESS,
                isSelected = selectedEmotion == LargeTagType.EMOTION_SADNESS,
                onChipClick = { onEmotionSelected(LargeTagType.EMOTION_SADNESS) }
            )

            Spacer(modifier = Modifier.width(20.dp))

            EmotionChip(
                emotionType = LargeTagType.EMOTION_RELIEF,
                isSelected = selectedEmotion == LargeTagType.EMOTION_RELIEF,
                onChipClick = { onEmotionSelected(LargeTagType.EMOTION_RELIEF) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, backgroundColor = 0xFF000000, name = "ByeBooChipBottomSheet Preview")
@Composable
fun ByeBooBottomSheetPreview() {

    ByeBooTheme {
        val previewSheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        )

        ByeBooBottomSheet(
            onDismiss = {},
            sheetState = previewSheetState,
            dragHandle = { ByeBooDragHandle() },
            isSelected = false,

        )
    }




}