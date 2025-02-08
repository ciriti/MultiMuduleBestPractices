package com.example.posttemplate.account.di

import com.example.posttemplate.account.data.repository.create
import com.example.posttemplate.account.data.service.create
import com.example.posttemplate.account.domain.repository.AccountRepository
import com.example.posttemplate.account.domain.service.AccountService
import com.example.posttemplate.account.ui.AccountViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val accountModule = module {

    // Repository
    single { AccountRepository.create() }

    // Services
    single { AccountService.create(accountRepository = get()) }

    // ViewModels
    viewModel { AccountViewModel(service = get()) }
}
