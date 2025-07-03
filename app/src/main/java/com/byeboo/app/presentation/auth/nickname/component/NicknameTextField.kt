package com.byeboo.app.presentation.auth.nickname.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.designsystem.ui.theme.error300
import com.byeboo.app.core.designsystem.ui.theme.gray300
import com.byeboo.app.core.designsystem.ui.theme.gray400
import com.byeboo.app.core.designsystem.ui.theme.primary300
import com.byeboo.app.core.designsystem.ui.theme.white
import com.byeboo.app.core.designsystem.ui.theme.whiteAlpha10
import com.byeboo.app.presentation.auth.nickname.ValidationState

@Composable
fun NicknameTextField(
    value: String,
    validationState: ValidationState,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColor = remember(validationState) {
        when (validationState) {
            ValidationState.Valid -> primary300
            ValidationState.Invalid -> error300
            ValidationState.Empty -> whiteAlpha10
        }
    }

    val shape = remember { RoundedCornerShape(12.dp) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, borderColor, shape)
                .clip(shape)
                .background(whiteAlpha10)
                .padding(horizontal = 24.dp, vertical = 18.dp)
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = ByeBooTheme.typography.body3.copy(color = white),
                singleLine = true,
                cursorBrush = SolidColor(white),
                visualTransformation = VisualTransformation.None,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth(),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (value.isEmpty()) {
                            Text(
                                text = "닉네임을 입력해주세요",
                                style = ByeBooTheme.typography.body3,
                                color = gray300
                            )
                        }
                        innerTextField()
                    }
                }
            )

            if (validationState == ValidationState.Invalid) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_error),
                    contentDescription = "Invalid",
                    tint = Color.Unspecified,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val guideColor = when (validationState) {
            ValidationState.Valid -> primary300
            ValidationState.Invalid -> error300
            ValidationState.Empty -> gray400
        }

        if (validationState == ValidationState.Valid) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "* 설정 가능한 닉네임이에요!",
                    style = ByeBooTheme.typography.cap2,
                    color = guideColor,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "${value.length}/5",
                    style = ByeBooTheme.typography.cap2,
                    color = gray400
                )
            }
        } else {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "* 2자 이상 5자 이하, 영어 또는 숫자 또는 한글로 구성",
                        style = ByeBooTheme.typography.cap2,
                        color = guideColor,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${value.length}/5",
                        style = ByeBooTheme.typography.cap2,
                        color = guideColor
                    )
                }

                Text(
                    text = "* 한글 초성 및 모음은 허가하지 않음",
                    style = ByeBooTheme.typography.cap2,
                    color = guideColor,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun NicknameTextFieldPreview() {
    ByeBooTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            NicknameTextField(
                value = "",
                validationState = ValidationState.Empty,
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            NicknameTextField(
                value = "하츠",
                validationState = ValidationState.Valid,
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(16.dp))
            NicknameTextField(
                value = "하츠핑♡",
                validationState = ValidationState.Invalid,
                onValueChange = {}
            )
        }
    }
}
