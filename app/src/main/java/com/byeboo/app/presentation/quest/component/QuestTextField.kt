package com.byeboo.app.presentation.quest.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.presentation.quest.record.QuestRecordingState
import com.byeboo.app.presentation.quest.record.QuestRecordingWritingState

@Composable
fun QuestTextField(
    textFieldBorderColor: Color,
    textCountColor: Color,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    isQuestion: Boolean = true
) {
    val currentCharCount = value.length
    val maxCharCount = if (isQuestion) 500 else 200

    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(290.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = textFieldBorderColor, shape = RoundedCornerShape(12.dp))
            .background(color = ByeBooTheme.colors.whiteAlpha10)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .verticalScroll(scrollState),
                textStyle = ByeBooTheme.typography.body3.copy(
                    color = ByeBooTheme.colors.white
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
                cursorBrush = SolidColor(ByeBooTheme.colors.white),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = ByeBooTheme.colors.gray300
                        )
                    }
                    innerTextField()
                }
            )

            Text(
                text = buildAnnotatedString {
                    append(text = "(")

                    append(text = currentCharCount.toString())

                    append(text = "/")

                    append(text = maxCharCount.toString())

                    append(text = ")")
                },
                style = ByeBooTheme.typography.body5,
                color = textCountColor,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
            )
        }
    }
}