package com.example.posttemplate.auth.ui

import androidx.lifecycle.viewModelScope
import com.example.posttemplate.auth.domain.service.AuthService
import com.example.posttemplate.ui.components.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthenticationViewModel(private val authService: AuthService) :
    BaseViewModel<AuthenticationState, AuthenticationEffect, AuthenticationIntent>() {

    private val _state = MutableStateFlow(AuthenticationState())
    override val state: StateFlow<AuthenticationState> = _state

    private val _effect = MutableSharedFlow<AuthenticationEffect>()
    override val effect: SharedFlow<AuthenticationEffect> = _effect

    override fun handleIntent(intent: AuthenticationIntent) {
        when (intent) {
            is AuthenticationIntent.Authenticate -> authenticate()
        }
    }

    private fun authenticate() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            delay(2000)

            val result = authService.login("email@example.com", "password") // Example usage
            if (result.isSuccess) {
                _effect.emit(AuthenticationEffect.NavigateToHome)
            } else {
                // Handle authentication failure
            }

            _state.value = _state.value.copy(isLoading = false)
        }
    }
}
