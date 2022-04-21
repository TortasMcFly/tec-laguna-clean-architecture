package com.tortasmcfly.teclaguna.feature_authentication.data.remote

import com.tortasmcfly.teclaguna.feature_authentication.data.util.Constants
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {

    @POST(AuthEndpoints.LOGIN)
    suspend fun login(
        @Body requestBody: RequestBody,
        @Header("Cookie") cookie: String = "${Constants.COOKIE_PREFIX}hola;"
    ): Response<ResponseBody>

}