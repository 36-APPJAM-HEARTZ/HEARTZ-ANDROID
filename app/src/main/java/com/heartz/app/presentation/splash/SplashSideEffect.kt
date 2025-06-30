package com.heartz.app.presentation.splash

sealed interface SplashSideEffect {
    data object NavigateToHome : SplashSideEffect
    data object NavigateToOnboarding : SplashSideEffect
}
