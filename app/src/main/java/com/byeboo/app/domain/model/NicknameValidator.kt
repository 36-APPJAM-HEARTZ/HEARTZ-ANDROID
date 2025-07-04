package com.byeboo.app.domain.model

sealed class NicknameValidationResult {
    object Empty : NicknameValidationResult()
    object Valid : NicknameValidationResult()
    object Invalid : NicknameValidationResult()
}

object NicknameValidator {
    private val regex = Regex("^[가-힣a-zA-Z0-9]{2,5}$")

    fun validate(input: String): NicknameValidationResult {
        return when {
            input.isBlank() -> NicknameValidationResult.Empty
            regex.matches(input) -> NicknameValidationResult.Valid
            else -> NicknameValidationResult.Invalid
        }
    }
}
