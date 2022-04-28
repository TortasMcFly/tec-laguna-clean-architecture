package com.tortasmcfly.teclaguna.feature_horario.domain.use_case

import com.tortasmcfly.teclaguna.feature_horario.domain.repository.HorarioRepository

class GetHorarioUseCase (
    private val repository: HorarioRepository
) {

    suspend operator fun invoke(sessionId: String) = repository.getMaterias(sessionId)

}