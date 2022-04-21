package com.tortasmcfly.teclaguna.feature_authentication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tortasmcfly.teclaguna.feature_authentication.domain.model.Student

@Entity(tableName = "student_table")
data class StudentEntity(
    var fullName: String? = null,
    var controlNumber: String? = null,
    var password: String? = null,
    var semester: String? = null,
    var specialityId: String? = null,
    var speciality: String? = null,
    var sessionId: String? = null,
    @PrimaryKey val id: Int? = null
)

fun Student.toDatabase() = StudentEntity(
    fullName = fullName,
    controlNumber = controlNumber,
    password = password,
    semester = semester,
    specialityId = specialityId,
    speciality = speciality,
    sessionId = sessionId
)

