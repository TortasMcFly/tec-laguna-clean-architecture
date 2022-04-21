package com.tortasmcfly.teclaguna.feature_authentication.presentation

import com.tortasmcfly.teclaguna.feature_authentication.domain.model.Student

data class LoginState(
    val student: Student? = null,
    val loading: Boolean = false
)
