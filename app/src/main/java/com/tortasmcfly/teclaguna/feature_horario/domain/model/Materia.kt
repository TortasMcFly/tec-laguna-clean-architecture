package com.tortasmcfly.teclaguna.feature_horario.domain.model

import java.util.ArrayList

data class Materia (
    var grupo: String? = null,
    var materia: String? = null,
    var profesor: String? = null,
    var lunes: String? = null,
    var martes: String? = null,
    var miercoles: String? = null,
    var jueves: String? = null,
    var viernes: String? = null
) {
    fun loadHorario(): List<Horario> {
        val horario = ArrayList<Horario>()

        lunes?.let {
            horario.add(Horario(
                it.substring(0, it.indexOf("-")),
                it.substring(it.indexOf("-"), it.indexOf("/")),
                it.substring(it.indexOf("/"), it.length)
            ))
        }

        martes?.let {
            horario.add(Horario(
                it.substring(0, it.indexOf("-")),
                it.substring(it.indexOf("-"), it.indexOf("/")),
                it.substring(it.indexOf("/"), it.length)
            ))
        }

        miercoles?.let {
            horario.add(Horario(
                it.substring(0, it.indexOf("-")),
                it.substring(it.indexOf("-"), it.indexOf("/")),
                it.substring(it.indexOf("/"), it.length)
            ))
        }

        jueves?.let {
            horario.add(Horario(
                it.substring(0, it.indexOf("-")),
                it.substring(it.indexOf("-"), it.indexOf("/")),
                it.substring(it.indexOf("/"), it.length)
            ))
        }

        viernes?.let {
            horario.add(Horario(
                it.substring(0, it.indexOf("-")),
                it.substring(it.indexOf("-"), it.indexOf("/")),
                it.substring(it.indexOf("/"), it.length)
            ))
        }

        return horario
    }
}