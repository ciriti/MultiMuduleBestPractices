package com.example.posttemplate.account.domain.service

import Account

interface AccountService {
    suspend fun getAccountById(id: Int): Result<Account>
    suspend fun updateAccount(account: Account): Result<Account>

    companion object
}
