package com.example.posttemplate.ui.navigation

import androidx.lifecycle.ViewModel
import com.example.posttemplate.auth.data.repository.AuthRepository

class DrawerViewModel(val authRepository: AuthRepository) : ViewModel() {
    fun logOut() {
        authRepository.setUserSignedIn(false)
    }
}
