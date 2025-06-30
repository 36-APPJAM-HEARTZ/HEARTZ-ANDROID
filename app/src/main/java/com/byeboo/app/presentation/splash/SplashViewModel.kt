package com.byeboo.app.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.byeboo.app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _sideEffect = MutableSharedFlow<SplashSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        checkUserStatusAndNavigate()
    }

    private fun checkUserStatusAndNavigate() {
        viewModelScope.launch {
            delay(1000)

            val isLoggedIn = userRepository.isLoggedIn()

            val effect = if (isLoggedIn) {
                SplashSideEffect.NavigateToHome
            } else {
                SplashSideEffect.NavigateToOnboarding
            }

            _sideEffect.emit(effect)
        }
    }
}
