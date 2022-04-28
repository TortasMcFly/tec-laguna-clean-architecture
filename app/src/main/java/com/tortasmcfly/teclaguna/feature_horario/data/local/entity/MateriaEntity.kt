package com.tortasmcfly.teclaguna.feature_horario.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tortasmcfly.teclaguna.feature_horario.domain.model.Materia

@Entity(tableName = "materia_table")
data class MateriaEntity(
    var grupo: String? = null,
    var materia: String? = null,
    var profesor: String? = null,
    var lunes: String? = null,
    var martes: String? = null,
    var miercoles: String? = null,
    var jueves: String? = null,
    var viernes: String? = null,
    @PrimaryKey(autoGenerate = true) var id: Int? = null
)

fun Materia.toDatabase() = MateriaEntity(
    grupo = grupo,
    materia = materia,
    profesor = profesor,
    lunes = lunes,
    martes = martes,
    miercoles = miercoles,
    jueves = jueves,
    viernes = viernes
)

fun MateriaEntity.toDomain() = Materia(
    grupo = grupo,
    materia = materia,
    profesor = profesor,
    lunes = lunes,
    martes = martes,
    miercoles = miercoles,
    jueves = jueves,
    viernes = viernes
)