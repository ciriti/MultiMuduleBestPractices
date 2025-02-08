package com.example.posttemplate.auth.data.service

import com.example.posttemplate.auth.data.repository.AuthRepository
import com.example.posttemplate.auth.domain.service.AuthService
import com.google.firebase.auth.FirebaseAuth
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

class AuthServiceImplTest {

    private lateinit var authRepository: AuthRepository
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authService: AuthService

    @Before
    fun setUp() {
        authRepository = mockk(relaxed = true)
        firebaseAuth = mockk(relaxed = true)
        authService = AuthService.create(authRepository, /*firebaseAuth*/)
    }

    @Test
    fun `login should return success when authentication is successful`() = runBlocking {
        // Arrange
        every { authRepository.setUserSignedIn(true) } just Runs

        // Act
        val result = authService.login("email@example.com", "password")

        // Assert
        assertTrue(result.isSuccess)
        verify { authRepository.setUserSignedIn(true) }
    }

    @Test
    fun `register should return success when registration is successful`() = runBlocking {
        // Arrange
        every { authRepository.setUserSignedIn(true) } just Runs

        // Act
        val result = authService.register("email@example.com", "password")

        // Assert
        assertTrue(result.isSuccess)
        verify { authRepository.setUserSignedIn(true) }
    }

    @Test
    fun `logout should return success and set user signed out`() = runBlocking {
        // Arrange
        every { authRepository.setUserSignedIn(false) } just Runs

        // Act
        val result = authService.logout()

        // Assert
        assertTrue(result.isSuccess)
        verify { authRepository.setUserSignedIn(false) }
    }

    @Test
    fun `isLoggedIn should return true when user is signed in`() = runBlocking {
        // Arrange
        every { authRepository.isUserSignedIn() } returns true

        // Act
        val result = authService.isLoggedIn()

        // Assert
        assertTrue(result.getOrNull() == true)
    }

    @Test
    fun `loginWithGoogle should return success when Google login is successful`() = runBlocking {
        // Arrange
        every { authRepository.setUserSignedIn(true) } just Runs

        // Act
        val result = authService.loginWithGoogle("valid_token")

        // Assert
        assertTrue(result.isSuccess)
        verify { authRepository.setUserSignedIn(true) }
    }
}
