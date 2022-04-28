package com.tortasmcfly.teclaguna.feature_horario.data.local

import androidx.room.Database
import com.tortasmcfly.teclaguna.feature_horario.data.local.entity.MateriaEntity

@Database(
    entities = [MateriaEntity::class],
    version = 1
)
abstract class MateriaDatabase {
    abstract val dao: MateriaDao
}