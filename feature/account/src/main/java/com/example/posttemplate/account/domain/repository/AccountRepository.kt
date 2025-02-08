package com.example.posttemplate.account.domain.repository

import com.example.posttemplate.account.domain.model.Account

interface AccountRepository {
    suspend fun getAccountById(id: Int): Result<Account>
    suspend fun updateAccount(account: Account): Result<Account>

    companion object
}
