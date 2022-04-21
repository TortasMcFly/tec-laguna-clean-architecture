package com.tortasmcfly.teclaguna.feature_authentication.domain.use_case

import com.tortasmcfly.teclaguna.core.util.Resource
import com.tortasmcfly.teclaguna.feature_authentication.domain.model.InvalidFieldException
import com.tortasmcfly.teclaguna.feature_authentication.domain.model.Student
import com.tortasmcfly.teclaguna.feature_authentication.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlin.jvm.Throws

class LoginUseCase (private val repository: AuthRepository) {

    @Throws(InvalidFieldException::class)
    suspend operator fun invoke(controlNumber: String, password: String): Flow<Resource<Student>> {
        if(controlNumber.isBlank()) throw InvalidFieldException("Número de control inválido")
        if(password.isBlank()) throw InvalidFieldException("Contraseña inválida")
        return repository.login(controlNumber, password)
    }

}