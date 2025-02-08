package com.example.posttemplate.account.data.repository

import Account
import com.example.posttemplate.account.domain.repository.AccountRepository

fun AccountRepository.Companion.create(): AccountRepository = AccountRepositoryImpl()

private class AccountRepositoryImpl : AccountRepository {

    var mockAccount: Account = Account(
        id = 1,
        fullName = "carmelo Iriti",
        email = "ciiti@gmail.com",
        address = "KStraße 82, 10623 Berlin",
        phone = "0123456"
    )

    // Implement data fetching and updating logic here
    override suspend fun getAccountById(id: Int): Result<Account> = runCatching {
        mockAccount
    }

    override suspend fun updateAccount(account: Account): Result<Account> = runCatching {
        mockAccount = account
        mockAccount
    }
}
