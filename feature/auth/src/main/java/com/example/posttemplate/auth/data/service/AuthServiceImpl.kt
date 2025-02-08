package com.example.posttemplate.auth.data.service

import com.example.posttemplate.auth.data.repository.AuthRepository
import com.example.posttemplate.auth.domain.service.AuthService
import com.google.firebase.auth.FirebaseAuth

fun AuthService.Companion.create(authRepository: AuthRepository): AuthService = AuthServiceImpl(authRepository)

private class AuthServiceImpl(
    private val authRepository: AuthRepository,
    private val firebaseAuth: FirebaseAuth? = null // not nullable in production
) : AuthService {

    override suspend fun login(email: String, password: String): Result<Boolean> {
        return runCatching {
//            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val isSuccess = true // result.user != null
            authRepository.setUserSignedIn(isSuccess)
            isSuccess
        }
    }

    override suspend fun register(email: String, password: String): Result<Boolean> {
        return runCatching {
//            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val isSuccess = true // result.user != null
            authRepository.setUserSignedIn(isSuccess)
            isSuccess
        }
    }

    override suspend fun logout(): Result<Unit> {
        return runCatching {
//            firebaseAuth.signOut()
            authRepository.setUserSignedIn(false)
        }
    }

    override suspend fun isLoggedIn(): Result<Boolean> {
        return runCatching {
            authRepository.isUserSignedIn()
        }
    }

    override suspend fun loginWithGoogle(token: String): Result<Boolean> {
        return runCatching {
//            val credential = GoogleAuthProvider.getCredential(token, null)
//            val result = firebaseAuth.signInWithCredential(credential).await()
            val isSuccess = true // result.user != null
            authRepository.setUserSignedIn(isSuccess)
            isSuccess
        }
    }
}
