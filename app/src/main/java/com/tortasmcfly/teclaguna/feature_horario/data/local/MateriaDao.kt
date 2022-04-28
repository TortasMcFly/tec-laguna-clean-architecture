package com.tortasmcfly.teclaguna.feature_horario.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tortasmcfly.teclaguna.feature_horario.data.local.entity.MateriaEntity

@Dao
interface MateriaDao {

    @Query("SELECT * FROM materia_table")
    suspend fun getMaterias(): List<MateriaEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMaterias(materias: List<MateriaEntity>)

}