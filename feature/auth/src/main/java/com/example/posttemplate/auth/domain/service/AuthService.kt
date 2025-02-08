package com.example.posttemplate.auth.domain.service

interface AuthService {
    suspend fun login(email: String, password: String): Result<Boolean>
    suspend fun register(email: String, password: String): Result<Boolean>
    suspend fun logout(): Result<Unit>
    suspend fun isLoggedIn(): Result<Boolean>
    suspend fun loginWithGoogle(token: String): Result<Boolean>

    companion object
}
