package com.tortasmcfly.teclaguna.feature_authentication.domain.use_case

import com.tortasmcfly.teclaguna.feature_authentication.domain.repository.AuthRepository

class IsLoggedInUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke() = repository.isLoggedIn()

}