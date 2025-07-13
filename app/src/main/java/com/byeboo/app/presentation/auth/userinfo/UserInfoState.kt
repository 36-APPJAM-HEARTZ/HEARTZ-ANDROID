package com.byeboo.app.presentation.auth.userinfo

import com.byeboo.app.domain.model.Feeling
import com.byeboo.app.domain.model.NicknameValidationResult
import com.byeboo.app.domain.model.QuestStyle

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
