package com.tortasmcfly.teclaguna.feature_authentication.data.repository

import android.util.Log
import com.tortasmcfly.teclaguna.core.util.Resource
import com.tortasmcfly.teclaguna.feature_authentication.data.local.StudentDao
import com.tortasmcfly.teclaguna.feature_authentication.data.local.entity.toDatabase
import com.tortasmcfly.teclaguna.feature_authentication.data.remote.AuthApi
import com.tortasmcfly.teclaguna.feature_authentication.data.util.Constants
import com.tortasmcfly.teclaguna.feature_authentication.domain.model.Student
import com.tortasmcfly.teclaguna.feature_authentication.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl (
    private val api: AuthApi,
    private val dao: StudentDao
): AuthRepository {

    override suspend fun login(controlNumber: String, password: String): Flow<Resource<Student>> = flow {

        emit(Resource.Loading())

        try {

            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("tbLogin", controlNumber)
                .addFormDataPart("tbPassword", password)
                .addFormDataPart("Button2", Constants.BUTTON_2)
                .addFormDataPart("__EVENTVALIDATION", Constants.EVENTVALIDATION)
                .addFormDataPart("__VIEWSTATE", Constants.VIEWSTATE)
                .build()

            val response = api.login(requestBody)

            if(!response.isSuccessful) throw HttpException(response)
            val body = response.body() ?: return@flow

            val html = body.string()
            Log.e("response", html)

            if(html.contains("lblError")) {
                emit(Resource.Error("Usuario o contraseña icorrectos"))
                return@flow
            }

            val cookieList = response.raw().priorResponse?.headers?.values("Set-Cookie") ?: emptyList()

            if(cookieList.isNullOrEmpty()) {
                emit(Resource.Error("Usuario o contraseña icorrectos"))
                return@flow
            }

            var jsessionid = cookieList[0].split(";").toTypedArray()[0]
            jsessionid = jsessionid.replace("ASP.NET_SessionId=", "")

            val student = Student(
                controlNumber = controlNumber,
                password = password,
                sessionId = jsessionid
            )
            dao.insertStudent(student.toDatabase())
            emit(Resource.Success(student))
        }
        catch (httpException: HttpException) {
            emit(Resource.Error(httpException.message()))
        }
        catch (ioException: IOException) {
            emit(
                Resource.Error(
                    "No se pudo contactar con el servidor, revisa tu conexión a internet"
                )
            )
        }

    }

    override suspend fun logout(controlNumber: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            dao.deleteStudent(controlNumber)
            emit(Resource.Success(true))
        }
        catch (exception: Exception) {
            emit(Resource.Error(exception.message ?: "Ocurrió un error"))
        }
    }

    override suspend fun isLoggedIn(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val student = dao.getStudent()
            student?.let {
                emit(Resource.Success(true))
                return@flow
            }
            emit(Resource.Success(false))
        }
        catch (exception: Exception) {
            emit(Resource.Error(exception.message ?: "Ocurrió un error"))
        }
    }
}