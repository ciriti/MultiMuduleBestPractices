package com.example.posttemplate.account.ui

import com.example.posttemplate.account.domain.model.Account

data class AccountState(
    val isLoading: Boolean = false,
    val account: Account? = null,
    val errorMessage: String? = null
)

sealed class AccountIntent {
    data class LoadAccount(val accountId: Int) : AccountIntent()
    data class UpdateAccount(val account: Account) : AccountIntent()
}

sealed class AccountEffect {
    data class ShowError(val message: String) : AccountEffect()
    data object UpdateSuccess : AccountEffect()
}
