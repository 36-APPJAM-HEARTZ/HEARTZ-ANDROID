package com.byeboo.app.presentation.auth.userinfo.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.byeboo.app.presentation.auth.userinfo.component.UserInfoText
import com.byeboo.app.presentation.auth.userinfo.component.NicknameTextField
import com.byeboo.app.presentation.auth.userinfo.model.UserInfoValidationState

@Composable
fun UserInfoNicknameScreen(
    nickname: String,
    validationState: UserInfoValidationState,
    onTextChange: (String) -> Unit
) {
    Column {
        UserInfoText(
            title = "닉네임",
            guideText = "을 입력해주세요",
            contentText = "어떤 이름으로 불러드릴까요?"
        )
        NicknameTextField(
            value = nickname,
            validationState = validationState,
            onValueChange = onTextChange
        )
    }
}
