package com.byeboo.app.presentation.auth.userinfo.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.byeboo.app.R
import com.byeboo.app.core.designsystem.ui.theme.ByeBooTheme
import com.byeboo.app.core.util.screenHeightDp
import com.byeboo.app.core.util.screenWidthDp
import com.byeboo.app.presentation.auth.userinfo.component.NicknameTextField
import com.byeboo.app.presentation.auth.userinfo.model.UserInfoValidationState

@Composable
fun NicknameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    validationState: UserInfoValidationState,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val focusState = remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    val shape = remember { RoundedCornerShape(12.dp) }

    val borderColor = if (focusState.value) {
        when (validationState) {
            UserInfoValidationState.Valid -> ByeBooTheme.colors.primary300
            UserInfoValidationState.Invalid -> ByeBooTheme.colors.error300
            UserInfoValidationState.Empty -> ByeBooTheme.colors.whiteAlpha10
        }
    } else {
        Color.Transparent
    }

    val guideColor = when (validationState) {
        UserInfoValidationState.Valid -> ByeBooTheme.colors.primary300
        UserInfoValidationState.Invalid -> ByeBooTheme.colors.error300
        UserInfoValidationState.Empty -> ByeBooTheme.colors.gray400
    }

    val textFieldPadding =
    if (validationState == UserInfoValidationState.Invalid) 16.5.dp else 18.dp

    Column(modifier = modifier.padding(vertical = screenHeightDp(8.dp))) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(57.dp)
                .border(1.dp, borderColor, shape)
                .clip(shape)
                .background(ByeBooTheme.colors.whiteAlpha10)
                .padding(horizontal = screenWidthDp(24.dp), vertical = screenHeightDp(textFieldPadding))
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState.value = it.isFocused },
                textStyle = ByeBooTheme.typography.body3.copy(color = ByeBooTheme.colors.white),
                singleLine = true,
                cursorBrush = SolidColor(ByeBooTheme.colors.white),
                visualTransformation = VisualTransformation.None,
                keyboardOptions = keyboardOptions,
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                ),
                decorationBox = { innerTextField ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (value.isEmpty()) {
                                Text(
                                    text = "닉네임을 입력해주세요",
                                    style = ByeBooTheme.typography.body3,
                                    color = ByeBooTheme.colors.gray300
                                )
                            }
                            innerTextField()
                        }
                    }
                }
            )

            if (validationState == UserInfoValidationState.Invalid && focusState.value) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_error),
                    contentDescription = "Invalid",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.padding(bottom = screenHeightDp(16.dp)))

        if (validationState == UserInfoValidationState.Valid) {
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
                    color = ByeBooTheme.colors.gray400
                )
            }
        } else {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "* 공백 없이 영어, 숫자와 한글로 구성",
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
                    text = "* 2자 이상 5자 이하",
                    style = ByeBooTheme.typography.cap2,
                    color = guideColor
                )
            }
        }
    }
}

