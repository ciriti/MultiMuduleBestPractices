package com.example.posttemplate.auth.di

import com.example.posttemplate.auth.data.repository.AuthRepository
import com.example.posttemplate.auth.data.repository.create
import com.example.posttemplate.auth.data.service.create
import com.example.posttemplate.auth.domain.service.AuthService
import com.example.posttemplate.auth.ui.AuthenticationViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {

    // Repository
    single { AuthRepository.create(sharedPreferences = get()) }

    // Service
    single { AuthService.create(authRepository = get()) }

    // ViewModels
    viewModel { AuthenticationViewModel(authService = get()) }
}
