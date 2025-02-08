package com.example.posttemplate.account.data.service

import com.example.posttemplate.account.domain.model.Account
import com.example.posttemplate.account.domain.repository.AccountRepository
import com.example.posttemplate.account.domain.service.AccountService

fun AccountService.Companion.create(accountRepository: AccountRepository): AccountService =
    AccountServiceImpl(accountRepository)


private class AccountServiceImpl(
    private val accountRepository: AccountRepository
) : AccountService {
    override suspend fun getAccountById(id: Int): Result<Account> =
        accountRepository.getAccountById(id)

    override suspend fun updateAccount(account: Account): Result<Account> =
        accountRepository.updateAccount(account)
}
