package com.example.posttemplate.account.ui

import androidx.lifecycle.viewModelScope
import Account
import com.example.posttemplate.account.domain.service.AccountService
import com.example.posttemplate.ui.components.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountViewModel(
    private val service: AccountService
) : BaseViewModel<AccountState, AccountEffect, AccountIntent>() {

    private val _state = MutableStateFlow(AccountState())
    override val state: StateFlow<AccountState> = _state

    private val _effect = MutableSharedFlow<AccountEffect>()
    override val effect: SharedFlow<AccountEffect> = _effect

    override fun handleIntent(intent: AccountIntent) {
        when (intent) {
            is AccountIntent.LoadAccount -> loadAccount(intent.accountId)
            is AccountIntent.UpdateAccount -> updateAccount(intent.account)
        }
    }

    private fun loadAccount(accountId: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            service.getAccountById(accountId).fold(
                onFailure = { throwable ->
                    _state.value =
                        _state.value.copy(isLoading = false, errorMessage = throwable.message)
                    _effect.emit(AccountEffect.ShowError(throwable.message ?: "Unknown error"))
                },
                onSuccess = { account ->
                    _state.value =
                        _state.value.copy(isLoading = false, account = account, errorMessage = null)
                }
            )
        }
    }

    private fun updateAccount(account: Account) {
        viewModelScope.launch {
            service.updateAccount(account).fold(
                onFailure = { throwable ->
                    _effect.emit(AccountEffect.ShowError(throwable.message ?: "Update failed"))
                },
                onSuccess = {
                    // Update the state with the new account data
                    _state.update { currentState ->
                        currentState.copy(account = account)
                    }
                    // Emit an effect to indicate success
                    _effect.emit(AccountEffect.UpdateSuccess)
                }
            )
        }
    }
}
