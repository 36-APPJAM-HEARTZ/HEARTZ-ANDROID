package com.byeboo.app.presentation.auth.userinfo

import com.byeboo.app.domain.model.auth.Feeling
import com.byeboo.app.domain.model.auth.NicknameValidationResult
import com.byeboo.app.domain.model.auth.QuestStyle

data class UserInfoState(
    val nickname: String = "",
    val nicknameValidation: NicknameValidationResult = NicknameValidationResult.Empty,
    val selectedEmotion: Feeling? = null,
    val selectedQuest: QuestStyle? = null,
    val currentStep: Int = 0
)
sealed interface UserInfoSideEffect {
    data object NavigateToLoading : UserInfoSideEffect
}
