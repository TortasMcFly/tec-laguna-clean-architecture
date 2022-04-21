package com.tortasmcfly.teclaguna.feature_authentication.domain.repository

import com.tortasmcfly.teclaguna.core.util.Resource
import com.tortasmcfly.teclaguna.feature_authentication.domain.model.Student
import kotlinx.coroutines.flow.Flow

interface AuthRepository  {

    suspend fun login(
        controlNumber: String,
        password: String
    ): Flow<Resource<Student>>

    suspend fun logout(
        controlNumber: String
    ): Flow<Resource<Boolean>>

    suspend fun isLoggedIn(): Flow<Resource<Boolean>>

}