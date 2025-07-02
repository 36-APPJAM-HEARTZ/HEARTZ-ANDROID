package com.byeboo.app.presentation.auth.nickname

sealed class ValidationState {
    object Empty : ValidationState()
    object Valid : ValidationState()
    object InValid : ValidationState()
}
