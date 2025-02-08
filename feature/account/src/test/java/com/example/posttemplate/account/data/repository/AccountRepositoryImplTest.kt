package com.example.posttemplate.account.data.repository

import com.example.posttemplate.account.domain.model.Account
import com.example.posttemplate.account.domain.repository.AccountRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AccountRepositoryImplTest {

    private lateinit var accountRepository: AccountRepository

    @Before
    fun setUp() {
        accountRepository = AccountRepository.create()
    }

    @Test
    fun `getAccountById should return the correct account`() = runBlocking {
        // Arrange
        val expectedAccount = Account(
            id = 1,
            fullName = "carmelo Iriti",
            email = "ciiti@gmail.com",
            address = "KStraße 82, 10623 Berlin",
            phone = "0123456"
        )

        // Act
        val result = accountRepository.getAccountById(1)

        // Assert
        assertEquals(Result.success(expectedAccount), result)
    }

    @Test
    fun `updateAccount should update the account details`() = runBlocking {
        // Arrange
        val updatedAccount = Account(
            id = 1,
            fullName = "Updated Name",
            email = "ciiti@gmail.com",
            address = "Updated Address",
            phone = "9876543"
        )

        // Act
        val result = accountRepository.updateAccount(updatedAccount)

        // Assert
        assertEquals(Result.success(updatedAccount), result)
        assertEquals(updatedAccount, accountRepository.updateAccount(updatedAccount).getOrNull())
    }
}
