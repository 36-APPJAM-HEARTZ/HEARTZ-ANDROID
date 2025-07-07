package com.byeboo.app.presentation.auth.userinfo

import com.byeboo.app.domain.model.NicknameValidationResult

data class UserInfoState(
    val nickname: String = "",
    val nicknameValidation: NicknameValidationResult = NicknameValidationResult.Empty,
    val selectedEmotion: String? = null,
    val selectedQuest: String? = null,
    val currentStep: Int = 0
)
sealed interface UserInfoSideEffect {
    data object NavigateToLoading : UserInfoSideEffect
}
