package com.tortasmcfly.teclaguna.feature_authentication.presentation

sealed class LoginEvent {
    data class LoginStudent(val controlNumber: String, val password: String): LoginEvent()
}