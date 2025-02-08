package com.example.posttemplate.account.domain.model

data class Account(
    val id: Int,
    val fullName: String,
    val email: String,
    val address: String,
    val phone: String
)
