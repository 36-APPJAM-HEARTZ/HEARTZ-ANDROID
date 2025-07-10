package com.byeboo.app.presentation.auth.userinfo.model

import com.byeboo.app.domain.model.NicknameValidationResult

sealed class UserInfoValidationState {
    object Empty : UserInfoValidationState()
    object Valid : UserInfoValidationState()
    object Invalid : UserInfoValidationState()
}
fun NicknameValidationResult.toValidationState(): UserInfoValidationState {
    return when (this) {
        is NicknameValidationResult.Valid -> UserInfoValidationState.Valid
        is NicknameValidationResult.Invalid -> UserInfoValidationState.Invalid
        is NicknameValidationResult.Empty -> UserInfoValidationState.Empty
    }
}
