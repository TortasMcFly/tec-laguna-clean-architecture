package com.tortasmcfly.teclaguna.feature_authentication.domain.use_case

import com.tortasmcfly.teclaguna.feature_authentication.domain.repository.AuthRepository

class LogoutUseCase (private val repository: AuthRepository) {

    suspend operator fun invoke(controlNumber: String) =
        repository.logout(controlNumber)

}