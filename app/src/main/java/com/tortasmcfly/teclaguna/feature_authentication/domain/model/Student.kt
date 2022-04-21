package com.tortasmcfly.teclaguna.feature_authentication.domain.model

data class Student (
    var fullName: String? = null,
    var controlNumber: String? = null,
    var password: String? = null,
    var semester: String? = null,
    var specialityId: String? = null,
    var speciality: String? = null,
    var sessionId: String? = null,
)