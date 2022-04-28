package com.tortasmcfly.teclaguna.feature_horario.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST

interface MateriaApi {

    @POST(MateriaEndpoints.GET_CARGA_ACADEMICA)
    suspend fun getCargaAcademica(
        @Header("Cookie") cookie: String
    ): Response<ResponseBody>

}