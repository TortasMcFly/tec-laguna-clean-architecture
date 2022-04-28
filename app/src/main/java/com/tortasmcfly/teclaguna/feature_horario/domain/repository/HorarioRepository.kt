package com.tortasmcfly.teclaguna.feature_horario.domain.repository

import com.tortasmcfly.teclaguna.core.util.Resource
import com.tortasmcfly.teclaguna.feature_horario.domain.model.Materia
import kotlinx.coroutines.flow.Flow

interface HorarioRepository {

    suspend fun getMaterias(sessionId: String): Flow<Resource<List<Materia>>>

}