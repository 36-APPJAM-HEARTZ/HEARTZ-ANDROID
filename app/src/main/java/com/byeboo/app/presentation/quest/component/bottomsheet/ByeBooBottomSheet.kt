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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.component.button.ByeBooActivationButton
import com.byeboo.app.core.designsystem.component.chip.EmotionChip
import com.byeboo.app.core.designsystem.type.LargeTagType
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ByeBooBottomSheet(
    modifier: Modifier = Modifier,
    showBottomSheet: Boolean = false,
    navigateButton: () -> Unit,
    onDismiss: () -> Unit,
    onEmotionSelected: (LargeTagType) -> Unit = {},
    onSelectedChanged: (Boolean) -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    isBackgroundDimmed: Boolean = true,
    dragHandle: @Composable () -> Unit = {},
    isSelected: Boolean = false
) {
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismiss,
            modifier = modifier,
            sheetState = sheetState,
            containerColor = ByeBooTheme.colors.gray900Alpha80,
            scrimColor = if (isBackgroundDimmed) {
                ByeBooTheme.colors.blackAlpha80
            } else {
                Color.Transparent
            },
            dragHandle = dragHandle
        ) {
            var selectedEmotion by remember { mutableStateOf<LargeTagType?>(null) }

            LaunchedEffect(showBottomSheet) {
                if (showBottomSheet) {
                    selectedEmotion = null
                    onSelectedChanged(false)
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 17.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ByeBooDragHandle()

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
                        val newEmotion = if (selectedEmotion == it) null else it
                        selectedEmotion = newEmotion
                        onSelectedChanged(newEmotion != null)
                    }

                )

                Spacer(modifier = Modifier.height(37.dp))

                ByeBooActivationButton(
                    buttonDisableColor = ByeBooTheme.colors.whiteAlpha10,
                    buttonText = "완료",
                    buttonDisableTextColor = ByeBooTheme.colors.gray300,
                    onClick = {
                        selectedEmotion?.let { emotion ->
                            onEmotionSelected(emotion)
                            onDismiss()
                        }

                        navigateButton()
                    },
                    isEnabled = isSelected
                )
            }
        }
    }
}

@Composable
private fun EmotionChipList(
    selectedEmotion: LargeTagType?,
    onEmotionSelected: (LargeTagType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 62.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            EmotionChip(
                emotionType = LargeTagType.EMOTION_NEUTRAL,
                isSelected = selectedEmotion == LargeTagType.EMOTION_NEUTRAL,
                onChipClick = { onEmotionSelected(LargeTagType.EMOTION_NEUTRAL) }
            )

            Spacer(modifier = Modifier.width(20.dp))
            EmotionChip(
                emotionType = LargeTagType.EMOTION_SELF_AWARE,
                isSelected = selectedEmotion == LargeTagType.EMOTION_SELF_AWARE,
                onChipClick = { onEmotionSelected(LargeTagType.EMOTION_SELF_AWARE) }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            EmotionChip(
                emotionType = LargeTagType.EMOTION_SADNESS,
                isSelected = selectedEmotion == LargeTagType.EMOTION_SADNESS,
                onChipClick = { onEmotionSelected(LargeTagType.EMOTION_SADNESS) }
            )

            Spacer(modifier = Modifier.width(20.dp))

            EmotionChip(
                emotionType = LargeTagType.EMOTION_RELIEF,
                isSelected = selectedEmotion == LargeTagType.EMOTION_RELIEF,
                onChipClick = { onEmotionSelected(LargeTagType.EMOTION_RELIEF) }
            )
        }
    }
}
